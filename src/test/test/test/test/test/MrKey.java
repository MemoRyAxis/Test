package test.test.test.test.test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.SignatureException;

public class MrKey {

	public static void main(String[] args) throws Exception {
		String beforeDegist = "asdf";
		System.out.println("摘要前:" + beforeDegist);

		byte[] plainText = beforeDegist.getBytes("UTF8");

		// MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");

		// System.out.println("/n" + messageDigest.getProvider().getInfo());

		// 开始使用算法
		messageDigest.update(plainText);

		// 输出算法运算结果
		String afterDegist = new String(messageDigest.digest(), "UTF8");
		System.out.println("摘要后:" + afterDegist);

		System.out.println("--->\t ");

//		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
//		FileInputStream fis = new FileInputStream("C:/public.cer");
//		Certificate cert = certFactory.generateCertificate(fis);
//		System.err.println("转换成String后的证书信息：" + cert.toString());
		String before = "asdf";  
        byte[] plainText2 = before.getBytes("UTF8");  
 
        //形成RSA公钥对  
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");  
        keyGen.initialize(1024);  
        KeyPair key = keyGen.generateKeyPair();  
 
        //使用私钥签名**********************************************************  
        Signature sig = Signature.getInstance("SHA1WithRSA");  
        sig.initSign(key.getPrivate());//sig对象得到私钥  

        
        System.out.println("--->\t private key: \n" + key.getPrivate().toString() + "\n");
        System.out.println("--->\t private key: \n" + key.getPublic().toString() + "\n");

        //签名对象得到原始数据  
        sig.update(plainText2);//sig对象得到原始数据(现实中用的是原始数据的摘要，摘要的是单向的，即摘要算法后无法解密)  
        byte[] signature = sig.sign();//sig对象用私钥对原始数据进行签名，签名后得到签名signature  
	//System.out.println(sig.getProvider().getInfo());  


        String after1 = new String(signature, "UTF8");  
        System.out.println("/n用私钥签名后:"+after1);  
 
        //使用公钥验证
        key = keyGen.generateKeyPair(); 
        sig.initVerify(key.getPublic());//sig对象得到公钥 


        //签名对象得到原始信息 
        sig.update(plainText2);//sig对象得到原始数据(现实中是摘要)  
        try {  
            if (sig.verify(signature)) {//sig对象用公钥解密签名signature得到原始数据(即摘要)，一致则true  
                System.out.println("签名验证正确！！"+new String(plainText2, "UTF8"));  
            } else {  
                System.out.println("签名验证失败！！");  
            }  
        } catch (SignatureException e) {  
            System.out.println("签名验证失败！！");  
        }  

	}
}
