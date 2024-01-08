import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BitTreeTest {
	static ByteArrayOutputStream outContent; 
	BitTree bitTree = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
    	System.setOut(System.out);
    }
	
	private void initialize()
	{
	}
	
	static private FileReader openFile(String folder, String fileName, String extension) {
        File f = null;
        FileReader fr = null;
        try {
        	String path = folder + fileName + extension;
        	f = new File(path);
        	fr = new FileReader(f);	
        }
        catch (Exception e) {
        	e.printStackTrace();
        }		
        return fr;
	}
	
	static private boolean isTerminationChar(int character, boolean newlineIsTermination) {
		if ((character == -1) || 
			(newlineIsTermination && (character == '\n' || character == '\r'))) {
			return true;
		}
		return false;
	}
	
	static private void compareFiles(String fileName, String extension, String solutionFolder, String solutionExtension, boolean allowEndingNewline) {
		if (solutionExtension == null) {
			solutionExtension = extension;
		}
		FileReader solutionFR = openFile(solutionFolder, fileName, solutionExtension);
		assertTrue("Solution file is missing", solutionFR != null);
		FileReader testFR = openFile("./output/", fileName, extension);
		assertTrue("Test file " + fileName + extension + " not found", testFR != null);
		
		boolean finished = false;
		int position = 0;
		while (!finished) {
			int solutionInt = 0;
			int testInt = 0;
			try {
				solutionInt = solutionFR.read();
				testInt = testFR.read();
			}
			catch (Exception e) {
				assertTrue("Failed to read file " + fileName + extension, false);
			}
			assertTrue("Character at postion " + Integer.toString(position) + "in " + fileName + extension + " is not correct", 
					solutionInt == testInt || 
					(solutionInt == -1 && isTerminationChar(testInt, allowEndingNewline)) ||
					(testInt == -1 && isTerminationChar(solutionInt, allowEndingNewline)));
			position++;
			if (solutionInt == -1 || testInt == -1) {
				finished = true;
			}
		}
	}
	
	/* parseHashMap doesn't work if there's a comma or equals sign as a key or value in the map. It needs to be rewritten.
	 * 
	 */
	private static HashMap<String, String> parseHashMap(String folder, String fileName, String extension) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		FileReader fr = openFile(folder, fileName, extension);
		try {
	    	int characterInt = fr.read(); // inital '{'
	    	if (characterInt != -1) {
	        	while ( characterInt != -1 && characterInt != (int)'}') {
		    		String keyString = new String();
		    		
		    		// First read the key.
		    		// In case the key is the = character, always read a second character.
		    		characterInt = fr.read();
		    		if (characterInt != -1 && characterInt != (int)'}') {
			    		keyString += Character.toString((char)characterInt);
		    			characterInt = fr.read();
		    			while (characterInt != (int)'=' && characterInt != (int)'}' && characterInt != -1) {
		    	    		keyString += Character.toString((char)characterInt);
		        			characterInt = fr.read();
			    		} 
			    		
		    			// Read in the value.
				    	if (characterInt != -1) {
				    		characterInt = fr.read(); // first digit of code or number
				    		String valueString = new String();
				    		while (characterInt != (int)',' && characterInt != (int)'}' && characterInt != -1) {
				    			valueString += Character.toString((char)characterInt);
					    		characterInt = fr.read();
				    		}
				    		// Ignore the space after the comma
				    		if (characterInt == ',') {
					    		characterInt = fr.read();
				    		}
				    		hashMap.put(keyString, valueString);
			    		}
		    		}
	    		}
	    	}
 		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return hashMap;
	}
	
 
	private static void compareMaps(HashMap<String, String> solutionMap, HashMap<String, String> testMap) {
		Set<String> solutionKeySet = solutionMap.keySet();
		Set<String> testKeySet = testMap.keySet();
		int lengthDifference = solutionMap.values().size() - testMap.values().size();
		if (lengthDifference >= 0) {
			for(String s : solutionKeySet) {
				if (!testMap.containsKey(s)) {
					assertTrue("Key " + s + " is missing from the HashMap.\n", false);
				} else if (!solutionMap.get(s).equals(testMap.get(s))) {
					assertTrue("Value for Key " + s + "is incorrect, expected value = " + solutionMap.get(s) + " actual value = " + testMap.get(s) + "\n", false);
				}
			}
		}
		else {
			for(String s : testKeySet) {
				if (!solutionMap.containsKey(s)) {
					assertTrue("Key " + s + " is not expected in the HashMap.\n", false);
				} else if (!solutionMap.get(s).equals(testMap.get(s))) {
					assertTrue("Value for Key " + s + "is incorrect, expected value = " + solutionMap.get(s) + " actual value = " + testMap.get(s) + "\n", false);
				}
			}
		}
	}
	
	private static void CheckHistoMap(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
		
		HashMap<String, String> solutionHistoMap = parseHashMap("./AnswerFiles/", fileName, ".bwt.histoMap.txt");
		HashMap<String, String> testHistoMap = parseHashMap("./output/", fileName, ".bwt.histoMap.txt");
		compareMaps(solutionHistoMap, testHistoMap);
		
		//compareFiles(fileName, ".bwt.histoMap.txt", "./AnswerFiles/", null, true);
	}

	private static void CheckCodesMap(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}

		HashMap<String, String> solutionHistoMap = parseHashMap("./AnswerFiles/", fileName, ".bwt.codesMap.txt");
		HashMap<String, String> testHistoMap = parseHashMap("./output/", fileName, ".bwt.codesMap.txt");
		compareMaps(solutionHistoMap, testHistoMap);

		//compareFiles(fileName, ".bwt.codesMap.txt", "./AnswerFiles/", null, true);
	}	
	
	private static void checkBitsString(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
		
		compareFiles(fileName, ".bwt.bits.txt", "./AnswerFiles/", null, true);
	
	}	
	
	private static void checkBits(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
		
		compareFiles(fileName, ".bwt.bits", "./AnswerFiles/", null, true);
	
	}
	
	private static void checkDecompress(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
			bitTree.decompress();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
		
		compareFiles(fileName, ".bwt.decoded.txt", "./input/", ".txt", false);
	
	}
	
	private static void checkDecompressFromFile(String fileName) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
			BitTree bitTree2 = new BitTree(fileName, ".txt");
			bitTree2.decompress();
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
		
		compareFiles(fileName, ".bwt.decoded.txt", "./input/", ".txt", false);
	
	}	
	
	private static void checkInputFileSize(String fileName, double expected) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
			double size = bitTree.inputFileSize();
			assertTrue("Expected size = " + Double.toString(expected) + " Actual size = " + Double.toString(size), size == expected);
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
	}
	
	private static void checkCompressedFileSize(String fileName, double expected) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
			double size = bitTree.compressedFileSize();
			assertTrue("Expected size = " + Double.toString(expected) + " Actual size = " + Double.toString(size), size == expected);
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
	}
	
	private static void checkCompressionRatio(String fileName, double expected) {
		try {
			BitTree bitTree = new BitTree(fileName, ".txt");
			bitTree.encode();
			bitTree.compress();
			double size = bitTree.compressionRatio();
			assertTrue("Expected size = " + Double.toString(expected) + " Actual size = " + Double.toString(size), size == expected);
		}
		catch (Exception e) {
			assertTrue("Unexpected exception thrown" + e.getStackTrace(), false);
		}
	}
	
	@Test
	public void histoMapTestexample() 
	{
		CheckHistoMap("example");
	}
	
	@Test
	public void histoMapTestexample2() 
	{
		CheckHistoMap("example2");
	}
	
	@Test
	public void histoMapTestexample3() 
	{
		CheckHistoMap("example3");
	}

	@Test
	public void histoMapTestSimple() 
	{
		CheckHistoMap("simple test");
	}

//--------
	
	@Test
	public void codesMapTestexample() 
	{
		CheckCodesMap("example");
	}
	
	@Test
	public void codesMapTestexample2() 
	{
		CheckCodesMap("example2");
	}
	
	@Test
	public void codesMapTestexample3() 
	{
		CheckCodesMap("example3");
	}
	
	
	@Test
	public void codesMapTestSimple() 
	{
		CheckCodesMap("simple test");
	}
	
//-------------------------	
	
	
	@Test
	public void bitsStringTestexample() 
	{
		checkBitsString("example");
	}
	
	@Test
	public void bitsStringTestexample2() 
	{
		checkBitsString("example2");
	}
	
	@Test
	public void bitsStringTestexample3() 
	{
		checkBitsString("example3");
	}

	@Test
	public void bitsStringTestSimple() 
	{
		checkBitsString("simple test");
	}	

	//-------------------------	
	
		
		@Test
		public void bitsTestexample() 
		{
			checkBits("example");
		}
		
		@Test
		public void bitsTestexample2() 
		{
			checkBits("example2");
		}
		
		@Test
		public void bitsTestexample3() 
		{
			checkBits("example3");
		}
		
		@Test
		public void bitsTestSimple() 
		{
			checkBits("simple test");
		}	

//-------------------------	
		
		
		@Test
		public void decompressTestexample() 
		{
			checkDecompress("example");
		}
		
		@Test
		public void decompressTestexample2() 
		{
			checkDecompress("example2");
		}
		
		@Test
		public void decompressTestexample3() 
		{
			checkDecompress("example3");
		}
		
		@Test
		public void decompressTestSimple() 
		{
			checkDecompress("simple test");
		}	
		
//-------------------------	
		
			
			@Test
			public void decompressFromFileTestexample() 
			{
				checkDecompressFromFile("example");
			}
			
			@Test
			public void decompressFromFileTestexample2() 
			{
				checkDecompressFromFile("example2");
			}
			
			@Test
			public void decompressFromFileTestexample3() 
			{
				checkDecompressFromFile("example3");
			}
			
			@Test
			public void decompressFromFileTestSimple() 
			{
				checkDecompressFromFile("simple test");
			}	
//------------------			

			@Test
			public void fileSizeDocExample() {
				checkInputFileSize("docExample", 10.0);
			}
		
			@Test
			public void compressedFileSizeDocExample() {
				checkCompressedFileSize("docExample", 4);
			}
		
			@Test
			public void compressionRatioDocExample() {
				checkCompressionRatio("docExample", 0.4);
			}
		
}

