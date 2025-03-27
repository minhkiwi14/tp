package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.fasterxml.jackson.core.JsonProcessingException;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.testutil.SerializableTestClass;
import seedu.address.testutil.TestUtil;

/**
 * Tests JSON Read and Write
 */
public class JsonUtilTest {

    private static final Path SERIALIZATION_FILE = TestUtil.getFilePathInSandboxFolder("serialize.json");

    @TempDir
    Path tempDir;

    // Simple test class for JSON serialization
    private static class TestObject {
        public String name;
        public int value;
        
        public TestObject() {} // Required for Jackson
        
        public TestObject(String name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) return true;
            if (!(other instanceof TestObject)) return false;
            TestObject that = (TestObject) other;
            return this.name.equals(that.name) && this.value == that.value;
        }
    }

    @Test
    public void serializeObjectToJsonFile_noExceptionThrown() throws IOException {
        SerializableTestClass serializableTestClass = new SerializableTestClass();
        serializableTestClass.setTestValues();

        JsonUtil.serializeObjectToJsonFile(SERIALIZATION_FILE, serializableTestClass);

        assertEquals(FileUtil.readFromFile(SERIALIZATION_FILE), SerializableTestClass.JSON_STRING_REPRESENTATION);
    }

    @Test
    public void deserializeObjectFromJsonFile_noExceptionThrown() throws IOException {
        FileUtil.writeToFile(SERIALIZATION_FILE, SerializableTestClass.JSON_STRING_REPRESENTATION);

        SerializableTestClass serializableTestClass = JsonUtil
                .deserializeObjectFromJsonFile(SERIALIZATION_FILE, SerializableTestClass.class);

        assertEquals(serializableTestClass.getName(), SerializableTestClass.getNameTestValue());
        assertEquals(serializableTestClass.getListOfLocalDateTimes(), SerializableTestClass.getListTestValues());
        assertEquals(serializableTestClass.getMapOfIntegerToString(), SerializableTestClass.getHashMapTestValues());
    }

    @Test
    public void jsonUtil_readJsonStringToObjectInstance_correctObject() throws JsonProcessingException {
        String jsonString = "{\"name\":\"Test\",\"value\":123}";
        TestObject expected = new TestObject("Test", 123);

        try {
            TestObject actual = JsonUtil.fromJsonString(jsonString, TestObject.class);

            assertNotNull(actual, "Deserialized object should not be null");
            assertEquals(expected, actual, "Deserialized object should match expected");
        } catch (IOException e) {
            fail("Exception should not have been thrown.");
        }
    }

    @Test
    public void jsonUtil_writeThenReadObjectToJson_correctObject() throws IOException, DataLoadingException {
        // Setup
        TestObject original = new TestObject("Test", 123);
        Path tempFile = tempDir.resolve("test.json");

        // Execute - Write then read
        JsonUtil.saveJsonFile(original, tempFile);
        TestObject deserialized = JsonUtil.readJsonFile(tempFile, TestObject.class)
                .orElseThrow(() -> new IOException("File not found"));

        // Verify
        assertNotNull(deserialized, "Deserialized object should not be null");
        assertEquals(original, deserialized, "Deserialized object should match original");
    }

    @Test
    public void readJsonFile_nonexistentFile_returnsEmptyOptional() throws DataLoadingException {
        Path invalidFile = tempDir.resolve("nonexistent.json");
        Optional<TestObject> result = JsonUtil.readJsonFile(invalidFile, TestObject.class);
        assertTrue(result.isEmpty(), "Should return empty Optional for nonexistent file");
    }

    @Test
    public void readJsonFile_invalidJson_throwsDataLoadingException() throws IOException {
        // Create a file with invalid JSON content
        Path invalidJsonFile = tempDir.resolve("invalid.json");
        Files.writeString(invalidJsonFile, "{invalid json}");

        assertThrows(DataLoadingException.class, () -> 
            JsonUtil.readJsonFile(invalidJsonFile, TestObject.class));
    }

    @Test
    public void fromJsonString_invalidJson_throwsJsonProcessingException() {
        String invalidJson = "{invalid}";
        assertThrows(JsonProcessingException.class, () ->
            JsonUtil.fromJsonString(invalidJson, TestObject.class));
    }
}
