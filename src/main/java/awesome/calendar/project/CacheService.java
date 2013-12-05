package awesome.calendar.project;

import static org.elasticsearch.client.Requests.createIndexRequest;
import static org.elasticsearch.index.query.FilterBuilders.queryFilter;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.OrFilterBuilder;
import org.elasticsearch.index.query.QueryFilterBuilder;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CacheService {

	final String type = "testtype";
	final String index = "testindex";

	ObjectMapper mapper = new ObjectMapper();
	Client client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

	public void init() {
		try {
			client.admin().indices().create(createIndexRequest(index)).actionGet();
		} catch (IndexAlreadyExistsException iaee) {
			// do nothing
		}
	}
	
	public List<String> processPriorities() {
		List<String> prioritiesList = Lists.newArrayList();
		for (Priority p : Priority.values()) {
			String priority = p.toString().toLowerCase();
			prioritiesList.add(priority);
		}
		return prioritiesList;
	}

	public void index(HashMap<String, Object> metadata) {
		try {
			String json = mapper.writeValueAsString(metadata);
			IndexResponse response = client.prepareIndex(index, type).setSource(json).execute().actionGet();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public List<HashMap<String, String>> searchName(String name) {
		List<HashMap<String, String>> answer = Lists.newArrayList();
		try {
			QueryFilterBuilder nameFilter = queryFilter(matchQuery("name", name));
			SearchResponse response = client.prepareSearch(index).setTypes(type).setFilter(nameFilter).execute().actionGet();
			long hits = response.getHits().getTotalHits();
			if (hits < 1) {
				// oops
			} else {
				JsonNode rootNode = mapper.readTree(response.toString());
				Iterator<JsonNode> hitsArray = rootNode.path("hits").path("hits").elements();
				while (hitsArray.hasNext()) {
					HashMap<String, String> hit = Maps.newHashMap();
					JsonNode metaNode = hitsArray.next();
					JsonNode sourceNode = metaNode.path("_source");
					hit.put("name", sourceNode.path("name").toString());
					hit.put("priority", sourceNode.path("priority").toString());
					hit.put("month", sourceNode.path("month").toString());
					hit.put("label", sourceNode.path("label").toString());
					hit.put("year", sourceNode.path("year").toString());
					hit.put("date", sourceNode.path("date").toString());
					hit.put("notes", sourceNode.path("notes").toString());
					hit.put("time", sourceNode.path("time").toString());
					hit.put("estimate", sourceNode.path("estimate").toString());
					answer.add(hit);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	public List<HashMap<String, String>> searchMonth(int month) {
		List<HashMap<String, String>> answer = Lists.newArrayList();
		try {
			QueryFilterBuilder monthFilter = queryFilter(matchQuery("month", month));
			SearchResponse response = client.prepareSearch(index).setTypes(type).setFilter(monthFilter).execute().actionGet();
			long hits = response.getHits().getTotalHits();
			if (hits < 1) {
				// oops
			} else {
				JsonNode rootNode = mapper.readTree(response.toString());
				Iterator<JsonNode> hitsArray = rootNode.path("hits").path("hits").elements();
				while (hitsArray.hasNext()) {
					HashMap<String, String> hit = Maps.newHashMap();
					JsonNode metaNode = hitsArray.next();
					JsonNode sourceNode = metaNode.path("_source");
					hit.put("name", sourceNode.path("name").toString().replaceAll("\"", ""));
					hit.put("priority", sourceNode.path("priority").toString().replaceAll("\"", ""));
					hit.put("month", sourceNode.path("month").toString());
					hit.put("label", sourceNode.path("label").toString().replaceAll("\"", ""));
					hit.put("year", sourceNode.path("year").toString());
					hit.put("date", sourceNode.path("date").toString());
					hit.put("time", sourceNode.path("time").toString());
					hit.put("estimate", sourceNode.path("estimate").toString());
					hit.put("notes", sourceNode.path("notes").toString().replaceAll("\"", "").replaceAll("\\|", "").replaceAll("\\t", "").replaceAll("\t", "").replaceAll("\\n", "").replaceAll("\n", ""));
					answer.add(hit);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	public List<HashMap<String, String>> searchWeek(long month, long weekOfMonth, long todayDate, long firstIntDayOfMonth) {
		List<HashMap<String, String>> answer = Lists.newArrayList();
		try {
			long firstDateOfWeek = todayDate - (7 * weekOfMonth) - firstIntDayOfMonth; 
			QueryFilterBuilder monthFilter = queryFilter(matchQuery("month", month));
			
			QueryFilterBuilder sundayFilter = queryFilter(matchQuery("date", firstDateOfWeek));
			QueryFilterBuilder mondayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 1));
			QueryFilterBuilder tuesdayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 2));
			QueryFilterBuilder wednesdayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 3));
			QueryFilterBuilder thursdayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 4));
			QueryFilterBuilder fridayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 5));
			QueryFilterBuilder saturdayFilter = queryFilter(matchQuery("date", firstDateOfWeek + 6));

			OrFilterBuilder orFilter = FilterBuilders.orFilter();
			orFilter.add(sundayFilter).add(mondayFilter).add(tuesdayFilter).add(wednesdayFilter).add(thursdayFilter).add(fridayFilter).add(saturdayFilter);
			AndFilterBuilder andFilter = FilterBuilders.andFilter();
			
			andFilter.add(monthFilter).add(orFilter);
			
			SearchResponse response = client.prepareSearch(index).setTypes(type).setFilter(andFilter).execute().actionGet();
			long hits = response.getHits().getTotalHits();
			if (hits < 1) {
				// oops
			} else {
				JsonNode rootNode = mapper.readTree(response.toString());
				Iterator<JsonNode> hitsArray = rootNode.path("hits").path("hits").elements();
				while (hitsArray.hasNext()) {
					HashMap<String, String> hit = Maps.newHashMap();
					JsonNode metaNode = hitsArray.next();
					JsonNode sourceNode = metaNode.path("_source");
					hit.put("name", sourceNode.path("name").toString().replaceAll("\"", ""));
					hit.put("priority", sourceNode.path("priority").toString().replaceAll("\"", ""));
					hit.put("month", sourceNode.path("month").toString());
					hit.put("label", sourceNode.path("label").toString().replaceAll("\"", ""));
					hit.put("year", sourceNode.path("year").toString());
					hit.put("date", sourceNode.path("date").toString());
					hit.put("time", sourceNode.path("time").toString().replaceAll("\"", ""));
					hit.put("estimate", sourceNode.path("estimate").toString());
					hit.put("notes", sourceNode.path("notes").toString().replaceAll("\"", "").replaceAll("\\|", "").replaceAll("\\t", "").replaceAll("\t", "").replaceAll("\\n", "").replaceAll("\n", ""));
					answer.add(hit);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	public void remove(String token) {
		try {
			// TODO: modify token
			QueryFilterBuilder tokenFilter = queryFilter(matchQuery("name", token));
			DeleteResponse response = client.prepareDelete(index, type, "*").execute().actionGet();
			// DeleteResponse response =
			// client.prepareDelete(index).setTypes(type).setFilter(tokenFilter).execute().actionGet();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
