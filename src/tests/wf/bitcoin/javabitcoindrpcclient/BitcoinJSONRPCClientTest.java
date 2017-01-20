package wf.bitcoin.javabitcoindrpcclient;

import org.junit.Before;
import org.junit.Test;

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
}