package jp.go.aist.rtm.systemeditor.ui.views.configurationview.configurationwrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * NamedValueを編集するためのラッパー
 *
 */
public class NamedValueConfigurationWrapper implements Comparable<NamedValueConfigurationWrapper> {

	private String key;

	private boolean isKeyModified = false;

	private String value;

	private boolean isValueModified = false;

	private List<ConfigurationWidget> widgetList;

	private Map<String, ConfigurationWidget> widgetMap;

	private boolean loadedWidgetValue;

	private String typeName;


	public NamedValueConfigurationWrapper(String key, String value, String typeName) {
		this.key = key;
		this.value = value;
		this.typeName = typeName;
	}

	public NamedValueConfigurationWrapper(String key) {
		this(key,null,null);
	}

	public NamedValueConfigurationWrapper(String key, String value) {
		this(key, value, null);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		if (this.key != null && this.key.equals(key)) {
			return;
		}
		this.key = key;
		isKeyModified = true;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (this.value != null && this.value.equals(value)) {
			return;
		}
		this.value = value;
		typeName = null;
		isValueModified = true;
	}

	public boolean isKeyModified() {
		return isKeyModified;
	}

	public boolean isValueModified() {
		return isValueModified;
	}

	/**
	 * widget種別、制約条件を設定します。
	 * @param widgets widget種別設定 (ConfigurationSetの_widget_で設定)
	 * @param conditions 制約条件 (ConfigurationSetの_<config名>で設定)
	 */
	public void setWidgetAndCondition(String widgets, String conditions) {
		ConfigurationCondition cc = ConfigurationCondition.NULL_CONDITION;
		try {
			cc = ConfigurationCondition.parse(conditions);
		} catch (ConfigurationCondition.NumberException e) {
		} catch (ConfigurationCondition.FormatException e) {
		}
		if (cc.isArrayCondition()) {
			// 配列型の場合
			widgetList = ConfigurationWidget.parseArrayWidget(widgets, cc);
		} else if (cc.isHashCondition()) {
			// ハッシュ型の場合
			widgetMap = ConfigurationWidget.parseHashWidget(widgets, cc);
		} else {
			widgetList = ConfigurationWidget.parseSimpleWidget(widgets, cc);
		}
	}

	/**
	 * デフォルトのwidgetを取得します。
	 * @return widgetオブジェクト
	 */
	public ConfigurationWidget widget() {
		return widget(0);
	}

	/**
	 * 配列の場合のwidgetを取得します。
	 * @param index widgetのインデックス
	 * @return widgetオブジェクト
	 */
	public ConfigurationWidget widget(int index) {
		return widgetList.get(index);
	}

	/**
	 * 配列の場合のwidget数を取得します。
	 * @return widget数。デフォルト時は1
	 */
	public int widgetSize() {
		if (widgetList == null)
			return 0;
		return widgetList.size();
	}

	/**
	 * ハッシュの場合のwidgetを取得します。
	 * @param key widgetのキー
	 * @return widgetオブジェクト
	 */
	public ConfigurationWidget widget(String key) {
		return widgetMap.get(key);
	}

	/**
	 * ハッシュの場合のwidgetのキーセットを取得します。
	 * @return widgetのキーセット
	 */
	public Set<String> widgetKeySet() {
		if (widgetMap == null)
			return Collections.emptySet();
		return widgetMap.keySet();
	}

	/**
	 * @return 編集用にwidgetにvalueを設定している場合はtrue
	 */
	public boolean isLoadedWidgetValue() {
		return loadedWidgetValue;
	}

	/**
	 * 編集用にvalueをwidgetに設定します。(配列、ハッシュは要素毎)
	 */
	public void loadWidgetValue() {
		// ハッシュの場合
		if (widgetMap != null) {
			Map<String, String> vm = parseMap(getValueAsString());
			for (String key : widgetMap.keySet()) {
				String value = vm.get(key);
				ConfigurationWidget w = widgetMap.get(key);
				w.setValue((value != null) ? value : "");
				w.clearValueModified();
			}
			loadedWidgetValue = true;
			return;
		}

		// widget種別、制約条件がない場合は空のwidgetを生成
		if (widgetList == null) {
			widgetList = new ArrayList<ConfigurationWidget>();
			ConfigurationWidget w = new ConfigurationWidget(ConfigurationWidget.TEXT,
					ConfigurationCondition.NULL_CONDITION);
			widgetList.add(w);
		}
		if (widgetList.size() == 1) {
			// 単体値の場合
			ConfigurationWidget w = widgetList.get(0);
			w.setValue(getValueAsString());
			w.clearValueModified();
		} else {
			// 配列の場合
			String v[] = getValueAsString().split(",");
			for (int i = 0; i < widgetList.size(); i++) {
				String value = (i < v.length) ? v[i] : "";
				ConfigurationWidget w = widgetList.get(i);
				w.setValue(value.trim());
				w.clearValueModified();
			}
		}
		loadedWidgetValue = true;
	}

	public String getValueAsString() {
		if (value == null) return typeName;
		return value;
	}

	/**
	 * widgetに設定した編集値をvalueに保存します。
	 */
	public void saveWidgetValue() {
		// ハッシュの場合
		if (widgetMap != null) {
			StringBuffer value = new StringBuffer("{");
			// キーをソート
			for (String key : widgetMap.keySet()) {
				ConfigurationWidget w = widgetMap.get(key);
				if (!value.toString().equals("{")) {
					value.append(", ");
				}
				value.append(key + ": " + w.getValue());
				w.clearValueModified();
			}
			value.append("}");
			setValue(value.toString());
			loadedWidgetValue = false;
			return;
		}

		if (widgetList == null) {
			return;
		}
		// 配列、単体の場合
		ConfigurationWidget w = widgetList.get(0);
		StringBuffer value = new StringBuffer(w.getValue());
		w.clearValueModified();
		for (int i = 1; i < widgetList.size(); i++) {
			w = widgetList.get(i);
			value.append(", " + w.getValue());
			w.clearValueModified();
		}
		setValue(value.toString());

		loadedWidgetValue = false;
	}

	/**
	 * {key:value,...}形式の文字列をマップにパースします。
	 */
	public Map<String, String> parseMap(String s) {
		Map<String, String> result = new HashMap<String, String>();
		if (s == null) {
			return result;
		}
		s = s.trim();
		if (s.length() == 0) {
			return result;
		}
		if (s.charAt(0) != '{') {
			return result;
		} else {
			s = s.substring(1);
		}
		if (s.charAt(s.length() - 1) != '}') {
			return result;
		} else {
			s = s.substring(0, s.length() - 1);
		}
		s = s.trim();
		String v[] = s.split(",");
		for (int i = 0; i < v.length; i++) {
			String vi = v[i].trim();
			String[] km = vi.split(":");
			if (km.length == 1 && vi.charAt(vi.length() - 1) == ':') {
				// キーのみ
				result.put(km[0].trim(), "");
			} else if (km.length == 2 && vi.charAt(0) != ':') {
				result.put(km[0].trim(), km[1].trim());
			}
		}
		return result;
	}

	@Override
	public NamedValueConfigurationWrapper clone() {
		NamedValueConfigurationWrapper result = new NamedValueConfigurationWrapper(key);
		result.setValue(getValueAsString());
		result.isKeyModified = isKeyModified;
		result.isValueModified = isValueModified;
		if (widgetList != null) {
			result.widgetList = new ArrayList<ConfigurationWidget>();
			for (ConfigurationWidget w : this.widgetList) {
				result.widgetList.add(w.clone());
			}
		}
		if (widgetMap != null) {
			result.widgetMap = new HashMap<String, ConfigurationWidget>();
			for (String key : widgetMap.keySet()) {
				ConfigurationWidget w = widgetMap.get(key);
				if (w != null) {
					result.widgetMap.put(key, w.clone());
				}
			}
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("key=").append(key).append(" keyModify=").append(isKeyModified)
				.append(" value=").append(getValueAsString()).append(" valueModify=")
				.append(isValueModified);
		if (widgetList != null) {
			buffer.append(" ").append(widgetList.toString());
		}
		if (widgetMap != null) {
			buffer.append(" ").append(widgetMap.toString());
		}
		buffer.append(" loadedWidgetValue=").append(loadedWidgetValue);
		return buffer.toString();
	}

	/**
	 * @see java.lang.Comparable#compareTo(Object)
	 */
	public int compareTo(NamedValueConfigurationWrapper object) {
		return new CompareToBuilder().append(this.key, object.key)
				.toComparison();
	}

	public boolean canModify() {
		if (value != null) return true;
		return typeName == null;
	}
}