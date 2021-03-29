package games.rogueLikeAVirgin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.AppLoader;

public class Loader {

	public static List<Score> restoreScores() {
		String json = AppLoader.restoreData("/rogueLikeAVirgin/scores.json");
		List<Score> scores = new ArrayList<Score>();
		try {
			JSONArray array = new JSONArray(json);
			for (int i = 0, li = array.length(); i < li; ++i) {
				try {
					JSONObject object = array.getJSONObject(i);
					String player = "";
					double count = 0;
					try {
						player = object.getString("player");
					} catch (JSONException error) {}
					try {
						count = object.getDouble("count");
					} catch (JSONException error) {}
					Score score = new Score(player, count);
					scores.add(score);
				} catch (JSONException error) {}
			}
		} catch (JSONException error) {}
		return scores;
	}

	public static void saveScores(List<Score> scores) {
		JSONArray array = new JSONArray();
		for (Score score: scores) {
			JSONObject object = new JSONObject();
			try {
				object.put("player", score.getPlayer());
			} catch (JSONException error) {}
			try {
				object.put("count", score.getCount());
			} catch (JSONException error) {}
			array.put(object);
		}
		String json = array.toString();
		AppLoader.saveData("/rogueLikeAVirgin/scores.json", json);
	}

}
