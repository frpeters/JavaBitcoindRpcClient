package wf.bitcoin.javabitcoindrpcclient;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

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
/*
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

    @Test
    public void keyPoolRefillTest() throws BitcoinRpcException {
        client.keyPoolRefill();
        client.keyPoolRefill(1000);
    }*/

    @Test
    public void getReceivedByAccountTest() throws BitcoinRpcException {
        assertEquals(BigDecimal.valueOf(0.0), client.getReceivedByAccount("tabby", 0));
        assertEquals(BigDecimal.valueOf(0.0), client.getReceivedByAccount("tabby", 6));
    }
/*
    @Test
    public void encryptWalletTest() throws BitcoinRpcException {
        client.encryptWallet("my pass phrase");
    }*/

/*
    @Test
    public void getWalletInfoTest() throws BitcoinRpcException {
        BitcoindRpcClient.WalletInfo wf = client.getWalletInfo();
        assertEquals(130000, wf.walletVersion());
        assertEquals(BigDecimal.valueOf(0.0), wf.balance());
        assertEquals(BigDecimal.valueOf(0.0), wf.unconfirmedBalance());
        assertEquals(BigDecimal.valueOf(0.0), wf.immatureBalance());
        assertEquals(0, wf.txCount());
        assertEquals(1485181120, wf.keyPoolOldest());
        assertEquals(101, wf.keyPoolSize());
        assertEquals(0, wf.unlockedUntil());
        assertEquals(BigDecimal.valueOf(0.0), wf.payTxFee());
        assertEquals("46e3f9cf241f4c6fea6268788837d66e429d213c", wf.hdMasterKeyId());
    }*/

    @Test
    public void getNetworkInfoTest() throws BitcoinRpcException {
        BitcoindRpcClient.NetworkInfo wf = client.getNetworkInfo();
        assertEquals(130100, wf.version());
        assertEquals("/Satoshi:0.13.1/", wf.subversion());
        assertEquals(70014, wf.protocolVersion());
        assertEquals("000000000000000d", wf.localServices());
        assertEquals(true, wf.localRelay());
        assertEquals(0, wf.timeOffset());
        assertEquals(8, wf.connections());
        assertEquals(BigDecimal.valueOf(0.00001000), wf.relayFee());
        assertEquals("", wf.warnings());
        assertTrue(wf.localAddresses().size() == 0);

        List<BitcoindRpcClient.Network> networks = wf.networks();
        assertTrue(networks.size() == 3);
        BitcoindRpcClient.Network net = networks.get(0);
        assertEquals("ipv4", net.name());
        assertEquals(false, net.limited());
        assertEquals(true, net.reachable());
        assertEquals("", net.proxy());
        assertEquals(false, net.proxyRandomizeCredentials());

        net = networks.get(1);
        assertEquals("ipv6", net.name());
        assertEquals(false, net.limited());
        assertEquals(true, net.reachable());
        assertEquals("", net.proxy());
        assertEquals(false, net.proxyRandomizeCredentials());

        net = networks.get(2);
        assertEquals("onion", net.name());
        assertEquals(true, net.limited());
        assertEquals(false, net.reachable());
        assertEquals("", net.proxy());
        assertEquals(false, net.proxyRandomizeCredentials());

    }

    @Test
    public void addMultiSigAddress() {
        List<String> keys = new LinkedList<String>();
        keys.add("1KQX9cBue11tmfjXSH3bnYtuk4hR9is3aq");
        keys.add("187hcDcxfHktaV5g8UFxT4ABwr6pKjBRyB");
        assertEquals("352a7HbefT2DFwM2gFmrp5PWFZyUxwCZSG", client.addMultiSigAddress(2, keys));
    }

    @Test
    public void createMultiSigTest() {
        List<String> keys = new LinkedList<String>();
        keys.add("1KQX9cBue11tmfjXSH3bnYtuk4hR9is3aq");
        keys.add("187hcDcxfHktaV5g8UFxT4ABwr6pKjBRyB");
        BitcoindRpcClient.MultiSig ms = client.createMultiSig(2, keys);
        assertEquals("352a7HbefT2DFwM2gFmrp5PWFZyUxwCZSG", ms.address());
        assertEquals("5221029fc4b75fb763a54dc232c8dea005866061cbec05aa38e1ce4ace23bd5387ca1421038c4181a4d7086a62ae3d5cc541848e7ebc87368ccd22fd910964551f180ccade52ae",
                    ms.redeemScript());
    }

    @Test
    public void verifyChainTest() {
        assertEquals(true, client.verifyChain());
    }

    @Test
    public void getAddedNodeInfoTest() {
        //client.addNode("nodoTest", "add");
        List<BitcoindRpcClient.NodeInfo> nodeInfoList = client.getAddedNodeInfo(true, "nodoTest");
        assertEquals("nodoTest", nodeInfoList.get(0).addedNode());
        assertEquals(false, nodeInfoList.get(0).connected());
    }

    @Test
    public void submitBlockTest() {
        try {
            client.submitBlock("mydata");
        }
        catch(Exception e) {
            assertThat(e.getMessage(), is("RPC Query Failed (method: submitblock, params: [mydata], response code: 500 responseMessage Internal Server Error, response: {\"result\":null,\"error\":{\"code\":-22,\"message\":\"Block decode failed\"},\"id\":\"1\"}\n"));
        }
    }

    //testear txout
}