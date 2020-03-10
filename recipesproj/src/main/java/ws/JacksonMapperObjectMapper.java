package ws;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonMapperObjectMapper {

	public ObjectMapper createObjectMapper() {
		ObjectMapper oMapper = new ObjectMapper();
		
		oMapper.setSerializationInclusion(org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL);
		
		return oMapper;
	}
	
}
