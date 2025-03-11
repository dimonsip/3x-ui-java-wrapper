# 3x-ui-wrapper

A fork of API wrapper for [3x-ui-wrapper](https://github.com/MHSanaei/3x-ui) written in Java by @megoRU

This fork simplifies the creation of inbounds and the generation of VLESS keys, along with other small improvements and fixes.

### Maven

https://clojars.org/org.clojars.dimonsip/3x-ui-java-wrapper

```xml

<dependency>
    <groupId>org.clojars.dimonsip</groupId>
    <artifactId>3x-ui-java-wrapper</artifactId>
    <version>1.8.5</version>
</dependency>
```

### | `Gradle`:
Repository:
```groovy
maven {
    name = "clojars.org"
    url = uri("https://repo.clojars.org")
}
```
Depend:
```groovy
implementation("org.clojars.dimonsip:3x-ui-java-wrapper:1.8.7")
```
## Preparing to upgrade 1.6.1 from previous versions

```html
inboundId now you need to specify it in the Client. 
If not specified, an exception will be thrown
```

## Examples

### Delete config

```java
public class Main {
    public static void main(String[] args) {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://45.15.158.18:2053")
                .setLogin("admin")
                .setPassword("admin")
                .enableDevMode()
                .build();

        Boolean b = threeUIAPI.deleteClient(1, "af444bd8-9b9b-46c4-9fcd-971153852d89"); //or email 432fdgd
        System.out.println(b);
    }
}
```

### Create config
```java
public class Main {
    public static void main(String[] args) {
        ThreeUIAPI threeUIAPI = new ThreeUIAPI.Builder().enableDevMode()
                .setHost("http://45.15.158.18:2053")
                .setLogin("admin")
                .setPassword("admin")
                .enableDevMode()
                .build();

        Client client = new Client.Builder()
                .email("testuser123")
                .method("aes-256-gcm")
                .enable(true)
                .subId("fdsfs432423")
                .inboundId(1)
                .password("APC8He0NRDfQp40BaLujKNmSANoOaJovQeWDVdsf")
                .build();

        Boolean status = threeUIAPI.addClient(client);
        System.out.println("status: " + status); //status:  true
    }
}
```
