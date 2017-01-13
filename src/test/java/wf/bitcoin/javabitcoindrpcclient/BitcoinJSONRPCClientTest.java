package wf.bitcoin.javabitcoindrpcclient;

import org.junit.Before;
import org.junit.Test;
import wf.bitcoin.krotjson.JSON;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by fpeters on 11-01-17.
 */



public class BitcoinJSONRPCClientTest {

    class MyClientTest extends BitcoinJSONRPCClient {

        String expectedMethod;
        Object[] expectedObject;
        String result;

        MyClientTest(boolean testNet, String expectedMethod, Object[] expectedObject, String result) {
            super(testNet);
            this.expectedMethod = expectedMethod;
            this.expectedObject = expectedObject;
            this.result = result;
        }

        @Override
        public Object query(String method, Object... o) throws BitcoinRpcException {
            if(method!=expectedMethod) {
                throw new BitcoinRpcException("wrong method");
            }
            if(o.equals(expectedObject)){
                throw new BitcoinRpcException("wrong object");
            }
            return JSON.parse(result);
        }
    }

    MyClientTest client;

    @Test
    public void getMiningInfoTest() throws Exception {
        client = new MyClientTest(false, "getmininginfo", null, "{\n" +
                "  \"blocks\": 448001,\n" +
                "  \"currentblocksize\": 0,\n" +
                "  \"currentblockweight\": 0,\n" +
                "  \"currentblocktx\": 0,\n" +
                "  \"difficulty\": 336899932795.8077,\n" +
                "  \"errors\": \"\",\n" +
                "  \"networkhashps\": 2.755614757831228e+18,\n" +
                "  \"pooledtx\": 11546,\n" +
                "  \"testnet\": false,\n" +
                "  \"chain\": \"main\"\n" +
                "}");
        BitcoindRpcClient.MiningInfo miningInfo = client.getMiningInfo();
        assertEquals(448001, miningInfo.blocks());
        assertEquals(0, miningInfo.currentBlockSize());
        assertEquals(0, miningInfo.currentBlockWeight());
        assertEquals(0, miningInfo.currentBlockTx());
        assertEquals(336899932795.8077, miningInfo.difficulty(), 0);
        assertEquals("", miningInfo.errors());
        assertEquals(Double.parseDouble("2.755614757831228e+18"), miningInfo.networkHashps(), 1);
        assertThat(miningInfo.pooledTx(), instanceOf(Integer.class));
        assertEquals(false, miningInfo.testNet());
        assertEquals("main", miningInfo.chain());
    }

    @Test
    public void getConnectionCountTest() throws Exception {
        client = new MyClientTest(false, "getconnectioncount", null, "8");
        long num = client.getConnectionCount();
        assertEquals(8, num);
    }

    @Test
    public void getUnconfirmedBalanceTest() throws Exception {
        client = new MyClientTest(false, "getunconfirmedbalance", null, "0.0");
        double num = client.getUnconfirmedBalance();
        assertEquals(0.0, num, 0);
    }

    @Test
    public void getUnconfirmedBalanceTestException() throws Exception {
        try {
            client = new MyClientTest(false, "getunconfirmedbalance", null, "12345678910L");
            double num = client.getUnconfirmedBalance();
        }
        catch(Exception e) {
            assertThat(e.getMessage(), is("java.lang.Long cannot be cast to java.lang.Double"));
        }
    }

    @Test
    public void getDifficultyTest() throws Exception {
        client = new MyClientTest(false, "getdifficulty", null, "336899932795.8077");
        double num = client.getDifficulty();
        assertEquals(336899932795.8077, num, 0);
    }

    @Test
    public void getDifficultyTest2() throws Exception {
        client = new MyClientTest(false, "getdifficulty", null, "12345678910L");
        double num = client.getDifficulty();
        assertEquals(12345678910L, num, 0);
    }

    @Test
    public void getDifficultyTestException() throws Exception {
        try{
            client = new MyClientTest(false, "getdifficulty", null, "'hello'");
            double num = client.getDifficulty();
        }
        catch (ClassCastException e){
            System.out.println(e);
            assertThat(e.getMessage(), is("java.lang.String cannot be cast to java.lang.Double"));
        }
    }

    /*
    @test
    public void signRawTransactionTest() throws Exception {
        String hex = client.signRawTransaction("01000000017EA599CE48E8A7F31A09158DD9C577F41A4C0E2A6588273C35ED8BC0184FBFAC000000007000483045022100E68B0357C55F38115627B837BF0D17B1D08EFCEA103F5D1550A15EB6A9A1C530022052BCEA3BAF63D5AF8DD6861B46B2EFE3C7B0764CF94BA326B467A398AE4E861B0125512103ABA8C372937CF73D311DB1750C80B93ABB82FC74116E373A946BE9ECC3AD0F0151AEFFFFFFFF012E260000000000001976A914F6D9195FCA0871BB8095598E5B78EBAAC1E3655F88AC00000000");

    }*/

}