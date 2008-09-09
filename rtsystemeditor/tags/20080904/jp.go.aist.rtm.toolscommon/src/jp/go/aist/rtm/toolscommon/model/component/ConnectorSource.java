package jp.go.aist.rtm.toolscommon.model.component;

import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;

import org.eclipse.emf.common.util.EList;

/**
 * 接続元を表現するクラス
 *
 * @model
 */
public interface ConnectorSource extends CorbaWrapperObject {
	/**
	 * <!-- begin-user-doc --> EList<? extends Connector> <!-- end-user-doc
	 * -->
	 * 
	 * @model type="Connector" containment="true" opposite="source"
	 */
	public EList getSourceConnectors();

	/**
	 * @model
	 */
	public boolean validateConnector(ConnectorTarget target);

}
