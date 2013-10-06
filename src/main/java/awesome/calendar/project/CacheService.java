package awesome.calendar.project;

import static org.elasticsearch.client.Requests.createIndexRequest;
import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.index.query.FilterBuilders.queryFilter;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Maps;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
					answer.add(hit);
				}
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	public List<HashMap<String, String>> searchMonth(String month) {
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
					hit.put("name", sourceNode.path("name").toString());
					hit.put("priority", sourceNode.path("priority").toString());
					hit.put("month", sourceNode.path("month").toString());
					hit.put("label", sourceNode.path("label").toString());
					hit.put("year", sourceNode.path("year").toString());
					hit.put("date", sourceNode.path("date").toString());
					hit.put("notes", sourceNode.path("notes").toString());
					answer.add(hit);
				}
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	@Deprecated
	public List<HashMap<String, String>> searchDate(String month, int date) {
		List<HashMap<String, String>> answer = Lists.newArrayList();
		try {
			QueryFilterBuilder monthFilter = queryFilter(matchQuery("month", month));
			QueryFilterBuilder dateFilter = queryFilter(matchQuery("date", String.valueOf(date)));
			SearchResponse response = client.prepareSearch(index).setTypes(type).setFilter(andFilter(monthFilter, dateFilter)).execute().actionGet();

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
					answer.add(hit);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return answer;
	}

	public void remove() {
	}

}
