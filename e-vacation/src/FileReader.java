import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class FileReader {

    public void writeJSON(ArrayList<Traveller> in_arraylist) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        AllTravellers data = new AllTravellers();
        data.setCollectionAllStudents(in_arraylist);
        mapper.writeValue(new File("abs_arraylist.json"), data);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Traveller> readJSON() throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        AllTravellers data = mapper.readValue(new File("abs_arraylist.json"), AllTravellers.class);
        return data.getCollectionAllTravellers();
    }
}

class AllTravellers {
        private ArrayList<Traveller> collectionAllTravellers;

    public ArrayList<Traveller> getCollectionAllTravellers() {
            return collectionAllTravellers;
        }

        public void setCollectionAllStudents(ArrayList<Traveller> collectionAllTravellers) {
            this.collectionAllTravellers = collectionAllTravellers;
        }
    }






