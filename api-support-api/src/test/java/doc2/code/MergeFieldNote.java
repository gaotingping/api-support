package doc2.code;

import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 负责合并父类字段注释
 */
public class MergeFieldNote {

	public static void merge(Map<String, JSONObject> allNote, Map<String, String> father) {
		Iterator<String> it = allNote.keySet().iterator();
		while (it.hasNext()) {
			String k = it.next();
			JSONObject v1 = allNote.get(k);
			if (v1 != null) {
				String k2 = father.get(k);
				while (true) {
					if (k2 == null) {
						break;
					}
					JSONObject v2 = allNote.get(k2);
					if (v2 != null) {
						v1.putAll(v2);
					}
					k2 = father.get(k2);
				}
			}
		}
	}
}
