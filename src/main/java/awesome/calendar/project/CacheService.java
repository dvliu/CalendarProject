
package awesome.calendar.project;

import static org.elasticsearch.index.query.FilterBuilders.andFilter;
import static org.elasticsearch.client.Requests.createIndexRequest;
import static org.elasticsearch.index.query.FilterBuilders.queryFilter;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.HashMap;
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
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CacheService {

	final String type = "testtype";
	final String index = "testndex";

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

	public List<HashMap<String, String>> search(String month, int date) {
		try {
			List<HashMap<String, String>> answer = Lists.newArrayList();
			QueryFilterBuilder monthFilter = queryFilter(matchQuery("month", month));
			QueryFilterBuilder dateFilter = queryFilter(matchQuery("date", date));
			SearchResponse response = client.prepareSearch(index).setTypes(type).setFilter(andFilter(monthFilter, dateFilter)).execute().actionGet();

			long hits = response.getHits().getTotalHits();
			if (hits < 1) {
				// oops
			}
			for (SearchHit hit : response.getHits()) {
				HashMap<String, String> metadata = Maps.newHashMap();
				String name = hit.field("name").getValue();
				String label = hit.field("label").getValue();
				String priority = hit.field("priority").getValue();
				metadata.put("name", name);
				metadata.put("label", label);
				metadata.put("priority", priority);
				answer.add(metadata);
			}
			return answer;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public void remove() {
	}

}
