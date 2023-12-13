package com.sy.web3.utils;

import org.junit.Before;
import org.junit.Test;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.sy.web3.utils.Solidity.packedKeccak256;
import static org.junit.Assert.assertEquals;

/**
 * TODO
 *
 * @Description
 * @Author Administrator
 * @Date 2023/7/8 0:22
 **/
public class MerklTreeTest {
    List<String> list;
    MerklTree merkleTree4Solidity;
//    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();

        list.add(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981));
        list.add(packedKeccak256("0x5d46Cc7813E97AC4379df8c1Fe30B8c875754aa0",711));
        list.add(packedKeccak256("0x9F7827F2EeD4E608d22057e03d6aAe593F0e47ae",9));
        list.add(packedKeccak256("0xA76f2B935C04Ab0D1c2EB16a4A7f410C656Cf645",1));
        list.add(packedKeccak256("0x5484B2df5674e2E3F9954C3972774fd2bF0A5326",170));
        list.add(packedKeccak256("0x03a6dfE7c7AA062F23B56657313571cD1CD4aDf9",1057));

        merkleTree4Solidity = new MerklTree(list, true);
        MerklTree merklTree = new MerklTree(list);
    }


    @Test
    public void findLeavesIndex() {
    }


    @Test
    public void getHexProof() {
        String root = merkleTree4Solidity.getHexRoot();
        assertEquals(root,"0x8c06e747aa414f1464660b7f1636f1091ee9784fa7cfc2b23ffede17b76c0f1b");
    }

    @Test
    public void getRoot() {
        String root = merkleTree4Solidity.getRoot();
        assertEquals(root,"8c06e747aa414f1464660b7f1636f1091ee9784fa7cfc2b23ffede17b76c0f1b");
    }

    @Test
    public void getHexRoot() {
    }


    @Test
    public void testVerify() {

        List<String> proof = merkleTree4Solidity.getProof(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981), null);
        List<String> hexProof = merkleTree4Solidity.getHexProof(packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47", 981), null);
        String root = merkleTree4Solidity.getRoot();
        boolean verify = merkleTree4Solidity.verify(proof, root, packedKeccak256("0xfc3921042358aC9a4092C4506bD20C6d9744DA47",981));
        assertEquals(verify,true);

    }

    @Test
    public void getProof() {
    }

    @Test
    public void testGetHexProof() {

//        "0x13ef58b4d3d01ae20d394a1997d841ddb300d070966300484841aa9a3b195320"
    }

    @Test
    public void testGetHexRoot() {
        String[] array =
                {
                        "ed070f8a7b6f3ff883bde472a835753c926475b4b76061b85e585affe5290963",
                        "a435552810effc0dad967c9e35fcfceb2399d05412150d1d62f8fde8578dcc3f",
                        "98bba67ca78c13d02601c39507436d277ba4a877e9f0bbf1734afc2b3f05163c",
                        "0a5cd6542b70d043cdf5d7ef4f6cc67e520bd50050a1602df039646e323dfdc2",
                        "18bbe3ec31faaefd6a43286c42800171f4c00d4e346aec339e0b7435a7a7becd",
                        "b331d8ffb6b68144a73a29babb7a56656e1f58429c7cd72ce0705c3d97ddb6dd",
                        "a17df7f99bf249e2a6678fc373320844c20d74703f05ba324bf4fcf34e85b0ab",
                        "83da4adccef0049e852adcb1f188d8a80b1cdcb108a8e9bd2e6473acfbc8f3f8",
                        "38a0c84bec1ea7a768ba4e6654d32739eec45e8891db3ba2bc9ec7ba00d55209",
                        "546180dfcfba1b55ca2b7d825f8ac9db3f84bfcd8d93c9448b4bf78df948ffa3",
                        "528843f0078143ae8646599cd72e7632f2a8c9916ff8c648b6146e5c465f198c",
                        "273cce0195b2e04e8bd38c9966737ecbaba26b8a78d2757d919e400f19043213",
                        "44875157c17e670dc9c10b844e78b15f50d33659f05210a95d385d9be7cd1f1d",
                        "31f42ac1f0409f85ffb3504632a75da31c226a4d15ac8dcaaeb4b80eb4624ab0",
                        "60494890f0820ddfe4367db28ffafab446f2ea1c8683d988497ee976dd9e4eb3",
                        "6e1d9d8a915ae2698e5c82459d3a4edf618c839926ed682d75c2a0b750f322bc",
                        "1a718bbe4de5581635851d077e698162cf5b07bbceeef635f4a95b5a4dcb4963",
                        "587bd359d8f1790bbad4ac9a4d215f31d723a73d12990c8085ceacb3eb1cc944",
                        "10ddbe71b1d06537cbc81eebf3ec368ddad39f45a86d91315c21ca2053533f50",
                        "1c317c566cc1ffc929212c039a3d5d011244ac89c0a8596b70e5e5e836240b09",
                        "1fcb7a9edd495900dc7936662f23967ffaed19f0df7ddf38cb9658f256e81856",
                        "82c2e383ee10ab280ccc4b32912374845d7f81d3fe48e0532f97b662c4b1bb58",
                        "7fb30ccf84daf49be9b1113594598f03a7ae66aefe0315986fbdd5a390cfb9db",
                        "75f7241bf2ef65d18b14c80cc8cbfb610a798c74b5da8842d62d174c16dd3900",
                        "28cbf7760c4c554ec5386dbc72b8c02174e495f0679b2ed3c8baeecd26cbcb0c",
                        "f9391d1febd55d3601b6d6d5434bc7c225b81402400fbf9ffb896bd3c1e16243",
                        "7d4db6461a1000c0c05761ee61e16fcf42dbcd326a8862fcb26f93b4998a7b7c",
                        "cdc87335a7aed17bab1bcd1e3dabea796be70f11a9f3421182e3b3350414b0cf",
                        "01cfb6df67072695e47b5e7ba01e5e2edef7eaf847537f5793977d20e5fc436c",
                        "d6a77ac605059aef3a340265a077e359666964286c4a670e6b670d8b8bdb93e0",
                        "16030f451e67366c55b3db140c13e2583cad93d1a19058d7dcb80b87ad67e6c6",
                        "9473290d5c421dd63a5590020cbc67d004ebc5b8b26a2304771b73da2550c93a",
                        "1659b22785fb7d68f902c6c75175ba6ad257091ae0fa5623bc35343e522c66c1",
                        "b55af46e96b8471a19a43e758087ca78eb33a8dd7035c0aea949ad6be6e6a54a",
                        "6b41b257a83993160b12f0f113a8e30ec25ef470021aa0a7459a491d0449e255",
                        "70db3b651e1a520dcdfe28e9e6571c787194aa472dec68b8ed7a37bc8db9e261",
                        "c4259b029ccda802491d75de9d21a36f53099c1441d576d440eb89df0f7dd3b4",
                        "0b47317259f2515a20ccf6158a606c0cbe2e3219e74502c4447b89113fb455c2",
                        "baa37a5a6e870788c307dc25bc295d91d830c5037823ce6a50f952a6a5d3d205",
                        "9073fe95f436072a76ec4b27c9c02f63c9c6ce137f3c1284f05356739a279661",
                        "479605710257c91114750cadef362f77329766d93cb36907a92ff8d17722cce4",
                        "64d20c374c25f0223ba86f60cda50bf883f72c958bd529d0bfcc95d12390b46e",
                        "0daf2711440e4633ce4d319babe8ffd18532da786b3c573de3b0a8f02f847c18",
                        "2916ceb255cd4d07e496ed5d87888ecf4694889f313f1993a4af07e410a68ff1",
                        "3eda7eac6748adb1fafd2aaf924f3d40df4b567e57f26189129850c009961482",
                        "1c244b047a4085f069583030206f31fe163900d66a05667a7b3498c7e282a8fe",
                        "97b4e049552b95e02de9764861456a1f2b761e763e175a9ae1987ea161c2e5c4",
                        "6faad38ead4851001d4e2397300a4681d0069ea88e8e1674d80c1f70755a306e",
                        "ec5e9dd48452a567bf5be82e5d7d1f8e95a8b64090d042d58f160f91d282b018",
                        "f9115882549258f92771567914a9614d760030b616bd92c45c461e78f97b35eb",
                        "c2f96cf27aa7d31588001af612c327e0f439a37f53414ecaa636ccd71190bcb0",
                        "ba247687440f81753c16290bc287b714ef7fe4d9423fafb46451fe6af9340fa2",
                        "cfbe0ef03aea02011db185dd665720426fb997341b0d13138b5118d3898b33cb",
                        "3dba74145b0ad9e8b19c3d189eb00130089fca2615f4c33c1dcc870754100d74",
                        "412ff99892d52cb506cada09afb9de8e98f7f060226508b394fc0dc5b323f7a8",
                        "d1b93a1ff1f7ee6e775ee7f8d7ecfeb8e95ddc793a42dc9df5cb28d06825f6d5",
                        "60390899fd2dc07cc0c5e0eda844a340a6143e67d46b3c82e5e794569e785cf0",
                        "26d728a88a18f66caa91ff1886f59e1d417f7c5d6e26670765df3d7a288a54ba",
                        "cd7d453f7aec549f89d1e2f638157479945750f3babf1ce0c57ad5d7cc3a3393",
                        "1eed197e43b69a8a572bebb4aa73c1f7fd20c279eefce13275be8c83a9f843e3",
                        "4f571025a27d1fe8c4c699c7a5f43a31215b991aba447968a4786e49fb008000",
                        "b1eb4919f2703302d0c16b76949884ca1d6032f697427c98f38bbc4cc1950468",
                        "c196567c900f723ccd0c77d320976dce188436255310b9cdc7d011f49e4aa194",
                        "2c6c75e030142bc25069d5da6d7b62a1150e7ee75684112a3dddf26ff481537f",
                        "c7f8f408c4aa60a9c966a688012cedfee0731c634a507c39f1d0ef5d6931064b",
                        "b1e5acf766f8bca2342921f9f9a48b0cdea08e9d32fddf3ca6a81e105b3a2e4f",
                        "420f9a30a62c68fc83961d2df4cc5cd3d9deea586cda2797e1281d54c56c1722",
                        "bf401ba2bd023147c5a52f51bdec353e5b5d80ee66211dd6a2cc7e2da6b8dcad",
                        "8912e93edd887d55c9887bb3a329094560f5a0e6ae591b1902b5a87be3df8445",
                        "7b6cd20aa373c8e7fc68bde7cc0d6467f07b795699debbd3f560b95d36ac8f89",
                        "797762139185ac36f124552fb365693e74b3cdaa555276312babf32bb23d8444",
                        "306653cc951e240597b3349e11f66dc63d70a070213a456b1e4f917792fb31ea",
                        "c0666c7667280cf7af561a29916e5912bbcfc725c709fce1a286fee304e9f93d",
                        "f8a32f1f60f859e952541db93fdc25fb8bba75f34656c579789347f9dff2f31e",
                        "f4b10088700cabaf94666dd3bd9ddd6db506ca2f73428c947799b6099401e2c2",
                        "52ba719a03ac2513f827fe13bafafd4d8f6e3a33c4bb9842ea7cd507fe9ecba0",
                        "5cadd00dff622df7183c30dd0735bed59a9d7d728652c21a2bd19bc78bfbdcf5",
                        "88d84ceec0658c4ca44265f829eb131f9fe3df851ddfeb2c42809d2e33c94812",
                        "555a0955a4da9eccc734b1ea14434b6778be2e4664d0814fb178630dfffa73bd",
                        "0fb69993c83d83ec1ede8bf6096eaa735a9fffab78ccbe662a67c6ba9173bc08",
                        "961821af3148ff93793ac6816a1b34d986e492f5b70d7fa8f7d9f063de9d67a2",
                        "9ab27536de7786a57bcf64d02d4779404580dea3e7e583db4202b47431e1f694",
                        "9bbc670b6aa960366303925c62f7598070022d17ba7d93b9c6b86bddf27dac60",
                        "15a63d3139cd1c1d174e239dca5c9d092188f802bb10ee382a6454247af767db",
                        "4f18a6120c201d6f1b9bf009219d39363d9c961d374f1e4cf800853bfd059d98",
                        "69a081c0126ebf8eee2364fb408129a8ae4256bc28b3beeeb871a9b0d8b8fb8f",
                        "850627f73084ad311b95c20396230135f1748f8c32cc20437a0fa3b1290b7e8e",
                        "52e7cece725918e3f1d902d45bfc64237a95d8c6eb95969d069513893ffdea6e",
                        "b5ed35d0dbc214402efff88f3fd0fffdb38bb1157ce4cc9dce91ff380470c8fc",
                        "cc3d19d3dc86405132c2f6b3c57725c1ed97e9d4ec0088d81e92986ef4c03216",
                        "4a4d0c4e4c5ef7bcc9b5210c14c0c50842cbbe6de915efb42d0a1f134be345c8",
                        "9f4f5b976c89e4105b16a8735308b5f57abdf9d486065ed18cec705523a0f3be",
                        "f80c5838fd1edfb998ac0cb9d15661130f1db5a15768943229e75381a0a0ad33",
                        "bba25565480bb71dbd454b951e74667b75f99516eb7b5249e7ae01ad2f7d5597",
                        "e87cecbe0dcc112dac38cfe4068b3c6de79b5850c1bf6bb5b1996c9cc6c8594c",
                        "cbc6707286421e4574311540cd816f110f20bddf623b7bd04ffcc8ceff239757",
                        "f462214d9ccccb0f73e1c70b6627db4e49ba502b2a56d31dd99ed23cc933ef73",
                        "244c8180c1b3da563276c4ac99ca97b845dc748b17af5efb83bcc46a038a33dd",
                        "9d2c308e5e93c87a885c25dd2c381d3aa8cac2da49d01cfa95fa87ad59f7f961",
                        "379a98699b43196bc4871fd9878b6ca4524699634f108ab0d589816709e2d567"
                     };
        List<String> stringList = Arrays.asList(array);
        MerklTree merklTree = new MerklTree(stringList);
        String hexRoot = merklTree.getHexRoot();
        System.out.println("hexroot:"+hexRoot);

        byte[] bytes = Numeric.hexStringToByteArray("0x328a8a3b10cecdfc49eeede9cfe6caed9b615c78bb3d2e4d4c012d14fb02aa4e");
        byte[] result = Hash.sha256(bytes);
        String s = Numeric.toHexString(result);
        System.out.println("s:"+s);
        List<String> hexProof = merklTree.getProof(
                "0xa17df7f99bf249e2a6678fc373320844c20d74703f05ba324bf4fcf34e85b0ab",
                null);
        hexProof.forEach(str->{
            System.out.println("hexProof:"+str);
        });
        boolean verify = merklTree.verify(hexProof, merklTree.getRoot(), "0xa17df7f99bf249e2a6678fc373320844c20d74703f05ba324bf4fcf34e85b0ab");
        merklTree.toString();
        System.out.println(verify);

    }
}
