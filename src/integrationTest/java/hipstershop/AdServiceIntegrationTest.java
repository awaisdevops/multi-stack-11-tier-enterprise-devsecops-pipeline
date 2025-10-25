package hipstershop;

import hipstershop.Demo.AdRequest;
import hipstershop.Demo.AdResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AdServiceIntegrationTest {

    private static AdService server;
    private static ManagedChannel channel;
    private static AdServiceGrpc.AdServiceBlockingStub blockingStub;

    @BeforeAll
    static void setUpServer() throws IOException, InterruptedException {
        int port = 9556; // Use a different port than default to avoid conflicts
        System.setProperty("PORT", String.valueOf(port));
        server = new AdService();
        server.start();
        
        // Give the server time to bind to the port and be ready to accept connections
        Thread.sleep(1000);

        channel = ManagedChannelBuilder.forAddress("localhost", port)
                .usePlaintext()
                .build();
        blockingStub = AdServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    static void tearDownServer() {
        channel.shutdownNow();
        server.stop();
    }

    @Test
    void testGetAds() {
        AdRequest request = AdRequest.newBuilder()
                .addContextKeys("clothing")
                .build();

        AdResponse response = blockingStub.getAds(request);

        assertFalse(response.getAdsList().isEmpty());
        assertEquals("/product/66VCHSJNUP", response.getAds(0).getRedirectUrl());
    }
}
