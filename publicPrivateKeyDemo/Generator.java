package publicPrivateKeyDemo;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.crypto.Cipher;

/**
 * @author Rob Allan
 *
 */


public class Generator {

	
	private static final String ALGORITHM = "RSA";
	   
	    public static byte[] encryptWithPublic(byte[] publicKey, byte[] inputData)
	            throws Exception {

	        PublicKey key = KeyFactory.getInstance(ALGORITHM)
	                .generatePublic(new X509EncodedKeySpec(publicKey));

	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.PUBLIC_KEY, key);

	        byte[] encryptedBytes = cipher.doFinal(inputData);

	        return encryptedBytes;
	    }

	    public static byte[] decryptWithPrivate(byte[] privateKey, byte[] inputData)
	            throws Exception {

	        PrivateKey key = KeyFactory.getInstance(ALGORITHM)
	                .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        cipher.init(Cipher.PRIVATE_KEY, key);

	        byte[] decryptedBytes = cipher.doFinal(inputData);

	        return decryptedBytes;
	    }

	    
	    public static byte[] encryptWithPrivate(byte[] privateKey, byte[] inputData)
	            throws Exception {

	    	PrivateKey key = KeyFactory.getInstance(ALGORITHM)
	                .generatePrivate(new PKCS8EncodedKeySpec(privateKey));

	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        //cipher.init(Cipher.PRIVATE_KEY, key);
	        cipher.init(Cipher.ENCRYPT_MODE, key);

	        byte[] encryptedBytes = cipher.doFinal(inputData);

	        return encryptedBytes;
	    }

	    public static byte[] decryptWithPublic(byte[] publicKey, byte[] inputData)
	            throws Exception {

	    	PublicKey key = KeyFactory.getInstance(ALGORITHM)
	                .generatePublic(new X509EncodedKeySpec(publicKey));

	        Cipher cipher = Cipher.getInstance(ALGORITHM);
	        //cipher.init(Cipher.PUBLIC_KEY, key);
	        
	        cipher.init(Cipher.DECRYPT_MODE, key);
	        
	        byte[] decryptedBytes = cipher.doFinal(inputData);

	        return decryptedBytes;
	    }
	    
	    public static KeyPair generateKeyPair()
	            throws NoSuchAlgorithmException, NoSuchProviderException {

	        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);

	        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

	        // 4096 is keysize
	        keyGen.initialize(4096, random);

	        KeyPair generateKeyPair = keyGen.generateKeyPair();
	        return generateKeyPair;
	    }

	    public static void main(String[] args) throws Exception {

	        KeyPair generateKeyPair = generateKeyPair();

	        byte[] publicKey = generateKeyPair.getPublic().getEncoded();
	        
	        // obviously you would not have WAM private key -added here to allow a private key to be generated
	        
	        
	        byte[] privateKey = generateKeyPair.getPrivate().getEncoded();
	        
	        // this could be replaced with a file that has the private key and get the bytes from it. 
	        
	        // here are the files that we will read and write from
	        
	        String FileInPath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\OPTUS OB OAM replacement\\Test Data\\sampleinputdata.txt";
	        
	        String FileOutPath = "C:\\Users\\Rob\\Userdata\\DX Software Testing\\Project Work\\OPTUS OB OAM replacement\\Test Data\\samploutputdata.txt";
	       
	        Path pathin = Paths.get(FileInPath);
	        byte[] datain = Files.readAllBytes(pathin);
	        
	        // Choose here to encrypt with Public and decrypt with private
	        byte[] encryptedData;
	        byte[] decryptedData;
	        boolean pubtoprivate = true; 
	        if (pubtoprivate){
	        encryptedData = encryptWithPublic(publicKey,datain);
	        
	        decryptedData = decryptWithPrivate(privateKey, encryptedData);
	        }
	        else
	        {
	        encryptedData = encryptWithPrivate(privateKey,datain);
	        
	        decryptedData = decryptWithPublic(publicKey, encryptedData);
	        }
	        //Path pathout = Paths.get("path/to/file");

	        //FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	         
	        FileOutputStream stream = new FileOutputStream(new File(FileOutPath));
	        try {
	            stream.write(decryptedData);
	        } finally {
	            stream.close();
	        }
	        
	        System.out.println(new String(encryptedData));
	        
	        System.out.println(new String(decryptedData));
	    }
}
