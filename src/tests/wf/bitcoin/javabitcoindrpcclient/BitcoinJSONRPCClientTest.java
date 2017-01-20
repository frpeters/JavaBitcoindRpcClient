package wf.bitcoin.javabitcoindrpcclient;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by fpeters on 19-01-17.
 */
public class BitcoinJSONRPCClientTest {

    BitcoinJSONRPCClient client;

    @Before
    public void setUp() {
        client = new BitcoinJSONRPCClient(false);
    }

    @Test
    public void getNetTotalsTest() throws Exception {
        BitcoinJSONRPCClient.NetTotalsImpl netTotals = (BitcoinJSONRPCClient.NetTotalsImpl) client.getNetTotals();
        assertEquals(86400, netTotals.uploadTarget().timeFrame());
        assertEquals(86400, netTotals.uploadTarget().timeFrame());
    }

    @Test
    public void decodeScriptTest() throws Exception {
        BitcoinJSONRPCClient.DecodedScriptImpl decodedScript = (BitcoinJSONRPCClient.DecodedScriptImpl) client.decodeScript("0100000001B8B2244FACA910C1FFFF24ECD2B559B4699338398BF77E4CB1FDEB19AD419EA0010000001976A9144CB4C3B90994FEF58FABB6D8368302E917C6EFB188ACFFFFFFFF012E2600000000000017A9140B2D7ED4E5076383BA8E98B9B3BCE426B7A2EA1E8700000000");
        assertEquals("0 0 0 -56 OP_CHECKSEQUENCEVERIFY 4faca910c1ffff24ecd2b559b4699338398bf77e4cb1fdeb19ad419ea0010000001976a9 4cb4c3b90994fef58fabb6d8368302e917c6efb1 OP_EQUALVERIFY OP_CHECKSIG OP_INVALIDOPCODE OP_INVALIDOPCODE OP_INVALIDOPCODE OP_INVALIDOPCODE 46 [error]", decodedScript.asm());
        assertEquals("nonstandard", decodedScript.type());
        assertEquals("3GYTuE1n5TD44EEp4n8SqCSFUzrxfBF51r", decodedScript.p2sh());
    }

    @Test
    public void pingTest() throws Exception {
        client.ping();
    }

    @Test
    public void getNetworkHashPsTest() throws BitcoinRpcException {
        System.out.println(client.getNetworkHashPs().toString());
    }

    @Test
    public void setTxFeeTest() throws BitcoinRpcException {
        assertEquals(true, client.setTxFee(BigDecimal.valueOf(134143.1123)));
    }

    @Test
    public void addNodeTest() throws BitcoinRpcException {
        client.addNode("192.167.0.6:8333", "add");
        client.addNode("192.167.0.6:8333", "onetry");
        client.addNode("192.167.0.6:8333", "remove");
    }

    @Test
    public void backupWalletTest() throws BitcoinRpcException {
        client.backupWallet("backupwalletfolder");
    }

    @Test
    public void signMessageTest() throws BitcoinRpcException {
        try {
            client.signMessage("1D1ZrZNe3JUo7ZycKEYQQiQAWd9y54F4XZ", "my message");
        }
        catch(Exception e) {
            assertThat(e.getMessage(), is("RPC Query Failed (method: signmessage, params: [1D1ZrZNe3JUo7ZycKEYQQiQAWd9y54F4XZ, my message], response code: 500 responseMessage Internal Server Error, response: {\"result\":null,\"error\":{\"code\":-4,\"message\":\"Private key not available\"},\"id\":\"1\"}\n"));
        }
    }

    @Test
    public void dumpImportWalletTest() throws BitcoinRpcException {
        client.dumpWallet("wallet example");
        client.importWallet("wallt example");
    }
}