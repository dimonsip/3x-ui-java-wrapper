package org.threexui;

import org.threexui.entity.api.*;
import org.threexui.entity.enums.FlowEnum;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;
import org.threexui.impl.ThreeUIAPI;
import org.threexui.utils.GenerateUtils;

import java.io.IOException;
import java.util.*;

public class LittleTest {

    public static void main(String[] args) throws UnsuccessfulHttpException, IOException, IllegalAccessException {
        String host = "localhost";
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder()
                .setHost("https://" + host + ":9000/api")
                .setLogin("admin")
                .setPassword("admin")
                .enableDevMode()
                .build();

        String randomUUID = UUID.randomUUID().toString();
        long totalGB = 200L * 1024 * 1024 * 1024;

        Inbound inbound = threeUIAPI.generateDefaultVlessInbound(
                randomUUID, randomUUID, totalGB, 0, "ShadowLine VPN", "wx.qq.com"
        );

        Boolean result_inbound_add = threeUIAPI.addInbound(inbound);
        System.out.printf("Inbound added %s \n", result_inbound_add);

        String vlessKey;
        vlessKey = GenerateUtils.vlessStringGenerate(host, inbound, null, "Test key");
        System.out.printf("VLESS Link for first key in inbound: \n%s \n", vlessKey);

        List<Inbound> inboundList = threeUIAPI.getInbounds();
        Inbound addedInbound = inboundList.get(0);

        String randomUUIDTwo = UUID.randomUUID().toString();
        Client client = new Client.Builder()
                .id(randomUUIDTwo)
                .inboundId((int) addedInbound.getId())
                .email(randomUUIDTwo)
                .flow(FlowEnum.XLTS_RPRX_VISION)
                .limitIp(1)
                .totalGB(totalGB)
                .enable(true)
                .build();
        Boolean result = threeUIAPI.addClient(client);
        System.out.printf("Client added %s \n", result);

        vlessKey = GenerateUtils.vlessStringGenerate(host, addedInbound, client, "Test key");
        System.out.printf("VLESS Link for second key in inbound: \n%s \n", vlessKey);

        String randomUUIDForDelete = UUID.randomUUID().toString();
        Client clientForDelete = new Client.Builder()
                .id(randomUUIDForDelete)
                .inboundId((int) addedInbound.getId())
                .email(randomUUIDForDelete)
                .flow(FlowEnum.XLTS_RPRX_VISION)
                .totalGB(totalGB)
                .enable(true)
                .build();
        threeUIAPI.addClient(clientForDelete);
        Boolean result_delete = threeUIAPI.deleteClient((int) addedInbound.getId(), randomUUIDForDelete);
        System.out.printf("Client deleted %s \n", result_delete);

        ClientSettings clientSettings = addedInbound.getSettings().getClients().get(0);
        Client client1 = Client.fromSettings(clientSettings, (int) addedInbound.getId(), null, null);
        client1.setEnable(false);

        Boolean result_update = threeUIAPI.updateClient(client1);
        System.out.printf("Client update result %s \n", result_update);

        Boolean result_reset_traffic = threeUIAPI.resetClientTraffics(client1.getInboundId(), client1.getId());
        System.out.printf("Client traffic reset result %s \n", result_reset_traffic);

        Boolean result_reset_all_traffic = threeUIAPI.resetInboundTraffics(addedInbound.getId());
        System.out.printf("All traffic reset %s \n", result_reset_all_traffic);

        Boolean result_inbound_delete = threeUIAPI.deleteInbound(37);
        System.out.printf("Inbound delete %s \n", result_inbound_delete);
    }
}
