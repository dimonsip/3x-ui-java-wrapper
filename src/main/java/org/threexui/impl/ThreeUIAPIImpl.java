package org.threexui.impl;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threexui.entity.api.Client;
import org.threexui.entity.api.ClientTraffics;
import org.threexui.entity.api.Inbound;
import org.threexui.entity.api.X25519Cert;
import org.threexui.entity.api.request.*;
import org.threexui.entity.api.response.*;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;
import org.threexui.utils.EntityUtils;
import org.threexui.utils.JsonUtil;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ThreeUIAPIImpl implements ThreeUIAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeUIAPIImpl.class);
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private final String host;
    private String cookie;
    private final String login;
    private final String password;
    private final boolean devMode;

    protected ThreeUIAPIImpl(String login, String password, boolean devMode, String host) throws UnsuccessfulHttpException, IOException {
        this.login = login;
        this.password = password;
        this.host = host;
        this.devMode = devMode;
        //Устанавливаем сессию
        setSession();
    }

    @Override
    public Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException {
        StatusResponse createClient = parseResponse(StatusResponse.class, new ClientCreateRequest(host, client));
        return createClient.isSuccess();
    }

    @Override
    public Boolean addInbound(@NotNull Inbound inbound) throws UnsuccessfulHttpException, IOException {
        StatusResponse createInbound = parseResponse(StatusResponse.class, new InboundCreateRequest(host, inbound));
        return createInbound.isSuccess();
    }

    @Override
    public Boolean deleteInbound(int inboundId) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteInboundRequest = parseResponse(StatusResponse.class, new DeleteInboundRequest(host, inboundId));
        return deleteInboundRequest.isSuccess();
    }

    @Override
    public Inbound generateDefaultVlessInbound(
            String id,
            String email,
            Long totalBytes,
            Integer limitIP,
            String remark,
            String sni
    ) throws UnsuccessfulHttpException, IOException {
        X25519Cert x25519Cert = getNewX25519Cert();
        return EntityUtils.createDefaultVlessInbound(id, email, totalBytes, limitIP, remark, x25519Cert, sni);
    }

    @Override
    public X25519Cert getNewX25519Cert() throws UnsuccessfulHttpException, IOException {
        X25519CertResponse x25519CertResponse = parseResponse(X25519CertResponse.class, new NewX25519CertRequest(host));
        return x25519CertResponse.getObj();
    }

    @Override
    public Boolean deleteClient(int inboundId, @NotNull String email) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new DeleteClientRequest(host, inboundId, email));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public ClientTraffics getClientTraffics(@NotNull String email) throws IOException, UnsuccessfulHttpException {
        ClientResponse clientResponse = parseResponse(ClientResponse.class, new ClientTrafficsRequest(host, email));
        return clientResponse.getObj();
    }

    @Override
    public List<String> getOnline() throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientsOnlineResponse.class, new OnlineClientsRequest(host)).getObj();
    }

    @Override
    public Boolean updateClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException {
        StatusResponse updateClient = parseResponse(StatusResponse.class, new ClientUpdateRequest(host, client));
        return updateClient.isSuccess();
    }

    @Override
    public List<Inbound> getInbounds() throws UnsuccessfulHttpException, IOException {
        return parseResponse(InboundResponse.class, new InboundRequest(host)).getObj();
    }

    @Override
    public Boolean resetInboundTraffics(long inboundId) throws UnsuccessfulHttpException, IOException {
        StatusResponse resetAllTraffics = parseResponse(StatusResponse.class, new ResetInboundTrafficsRequest(host, inboundId));
        return resetAllTraffics.isSuccess();
    }

    @Override
    public Boolean resetClientTraffics(long inboundId, String clientId) throws UnsuccessfulHttpException, IOException {
        StatusResponse resetAllTraffics = parseResponse(StatusResponse.class, new ResetClientTrafficsRequest(host, inboundId, clientId));
        return resetAllTraffics.isSuccess();
    }

    @Override
    public void setSession() throws IOException, UnsuccessfulHttpException {
        JSONObject json = new JSONObject();
        try {
            json.put("username", login);
            json.put("password", password);
        } catch (JSONException e) {
            LOGGER.error("setSession: ", e);
        }

        Request request = new Request.Builder()
                .url(String.format("%s/login", host))
                .post(RequestBody.create(json.toString(), MEDIA_TYPE_JSON))
                .build();
        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                cookie = response.header("Set-Cookie");
            } else {
                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private <T extends APIObject> T parseResponse(Class<T> tClass, @NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(apiRequest.getUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie);

        if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.GET) {
            requestBuilder = requestBuilder.get();
        } else if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.POST) {
            if (apiRequest.getData() != null) {
                requestBuilder.post(RequestBody.create(apiRequest.getData().toJson(), MEDIA_TYPE_JSON));
            } else {
                requestBuilder.post(RequestBody.create("{}", MEDIA_TYPE_JSON));
            }
        }

        Request request = requestBuilder.build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                logResponse(responseBody);
                if (!response.isSuccessful())
                    throw new UnsuccessfulHttpException(response.code(), response.body().string());
                return JsonUtil.fromJson(responseBody, tClass);
            } else {
                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private void logResponse(String body) {
        if (devMode) System.out.printf("Body: %s%n", body);
    }
}