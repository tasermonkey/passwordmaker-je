package org.daveware.passwordmaker.test;

import java.io.ByteArrayOutputStream;
import java.security.Security;

import junit.framework.Assert;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.daveware.passwordmaker.Account;
import org.daveware.passwordmaker.AlgorithmType;
import org.daveware.passwordmaker.CharacterSets;
import org.daveware.passwordmaker.LeetLevel;
import org.daveware.passwordmaker.LeetType;
import org.daveware.passwordmaker.RDFDatabaseWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class RDFDatabaseWriterTest {
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Security.addProvider(new BouncyCastleProvider());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testWriteAccount() throws Exception {
    	final String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
    			"<RDF:Description RDF:about=\"rdf:#$[B@5a676437\" NS1:name=\"Yahoo.com\" " + 
    			"NS1:description=\"Im a description\" NS1:whereLeetLB=\"off\" " +
    			"NS1:leetLevelLB=\"1\" NS1:hashAlgorithmLB=\"hmac-rmd160\" " +
    			"NS1:passwordLength=\"15\" NS1:usernameTB=\"myusername\" " +
    			"NS1:counter=\"Another Modifier\" " +
    			"NS1:charset=\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&amp;*()_-+={}|[]\\:&quot;;'&lt;&gt;?,./\" " +
    			"NS1:prefix=\"\" NS1:suffix=\"\" NS1:autoPopulate=\"false\" NS1:urlToUse=\"yahoo.com\"></RDF:Description>";
    	
    	
    	Account acc = new Account("Yahoo.com", "Im a description", "yahoo.com", "myusername", 
    			AlgorithmType.RIPEMD160, true, true, 15, CharacterSets.BASE_93_SET, 
    			LeetType.NONE, LeetLevel.LEVEL1, "Another Modifier", "", "", false, "rdf:#$[B@5a676437");
    	RDFDatabaseWriter writer = new RDFDatabaseWriter();
    	ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
    	writer.serializeAccount(os, acc);
    	String serializedString = os.toString("UTF-8");
    	Assert.assertEquals("Unexpected RDF came out of the RDFDatabaseWriter", expectedXml, serializedString);
    }
}
