package zenOSSJSONFormat;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import org.json.JSONObject;

public class Gson2Example {

    public static void main(String[] args) {

        Staff staff = createDummyObject();

        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(staff);
        System.out.println("Here is the GSON json");
        System.out.println(json);
        //String replace1 = json.replaceAll(".","_");
        //System.out.println(replace1);

        JSONObject jsonJSONORG = JSONObject.fromObject(staff);
        
        String jsonJSONORGstr = jsonJSONORG.toString();
        
        System.out.println("Here is the JSON-lib json");
        System.out.println(jsonJSONORGstr);
        jsonJSONORGstr = jsonJSONORGstr.replaceAll("java", "JAVA");
        jsonJSONORGstr = jsonJSONORGstr.replaceAll("python", "PYTHON");
        jsonJSONORG = JSONObject.fromObject(jsonJSONORGstr);
        System.out.println("After the replacements on the  is the JSON-lib json" + jsonJSONORG);
        
        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("C:\\Users\\Rob\\temp2.json")) {
        	
            //gson.toJson(staff, writer);
            StringBuilder jsonBuilder = new StringBuilder(json);
            //jsonBuilder.replace(start, end, str)
            //String string1 = json.replaceAll("jav", "JAV");
            //String string2 = string1.replaceAll("python", "PYTHPN");
            //String string3 = string2.replaceAll("\\","");
            
            //gson.toJson(string2, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Staff createDummyObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition("Founder");
        staff.setSalary(new BigDecimal("10000"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("shell");

        staff.setSkills(skills);

        return staff;

    }

}
