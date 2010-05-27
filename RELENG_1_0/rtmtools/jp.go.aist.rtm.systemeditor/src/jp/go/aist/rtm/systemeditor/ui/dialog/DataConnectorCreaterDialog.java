package jp.go.aist.rtm.systemeditor.ui.dialog;

import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.systemeditor.manager.SystemEditorPreferenceManager;
import jp.go.aist.rtm.systemeditor.nl.Messages;
import jp.go.aist.rtm.toolscommon.model.component.ComponentFactory;
import jp.go.aist.rtm.toolscommon.model.component.ComponentSpecification;
import jp.go.aist.rtm.toolscommon.model.component.ConnectorProfile;
import jp.go.aist.rtm.toolscommon.model.component.InPort;
import jp.go.aist.rtm.toolscommon.model.component.OutPort;
import jp.go.aist.rtm.toolscommon.util.ConnectorUtil;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * �f�[�^�|�[�g�Ԃ̐ڑ��̃R�l�N�^�v���t�@�C���̑I���_�C�A���O
 * <P>
 * �R�l�N�^�v���t�@�C���̑I���\�ȃ^�C�v���AOutPort�����InPort���画�f���v���_�E���Ƃ��ĕ\������B�i�uAny�v���͕̂\������Ȃ��j(�����̃P�[�X�͖������ă}�b�`���O���A�}�b�`�����ۂɕ\�������̂�OutPort�̕�����Ƃ���)<br>
 * �I���\�ł���̂́A�f�[�^�|�[�g�����ł���A�T�[�r�X�|�[�g�͊܂܂�Ȃ��B<br>
 * OutPort��������InPort�ɁuAny�v���܂܂�Ă���ꍇ�ɂ́A����̂��ׂČ^���󂯓������̂Ƃ���B<br>
 * OutPort�����Inport��Any���܂܂�Ă���ꍇ�ɂ́A�R���{�{�b�N�X�ɔC�ӂ̕��������͉\�Ƃ��A�u*�C�ӓ��͉v��\������B<br>
 * �T�u�X�N���v�V�����^�C�v�́A�f�[�^�t���[�^�C�v���uPush�v�̎��̂ݕ\�������B<br>
 * PushRate�́A�T�u�X�N���v�V�����^�C�v���uPeriodic�v�ł���A���f�[�^�t���[�^�C�v���uPush�v�̎��̂ݕ\�������<br>
 */
public class DataConnectorCreaterDialog extends TitleAreaDialog {

	static final String LABEL_PUSH_POLICY = Messages.getString("DataConnectorCreaterDialog.20");
	static final String LABEL_SKIP_COUNT = Messages.getString("DataConnectorCreaterDialog.21");
	static final String LABEL_DETAIL = Messages.getString("DataConnectorCreaterDialog.22");

	static final String LABEL_OUTPORT_BUFFER = Messages.getString("DataConnectorCreaterDialog.23");
	static final String LABEL_INPORT_BUFFER = Messages.getString("DataConnectorCreaterDialog.24");

	static final String LABEL_BUFFER_LENGTH = Messages.getString("DataConnectorCreaterDialog.25");
	static final String LABEL_BUFFER_FULL_POLICY = Messages.getString("DataConnectorCreaterDialog.26");
	static final String LABEL_BUFFER_WRITE_TIMEOUT = Messages.getString("DataConnectorCreaterDialog.27");
	static final String LABEL_BUFFER_EMPTY_POLICY = Messages.getString("DataConnectorCreaterDialog.28");
	static final String LABEL_BUFFER_READ_TIMEOUT = Messages.getString("DataConnectorCreaterDialog.29");

	static final String MSG_ERROR_PUSH_RATE_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.19");
	static final String MSG_ERROR_SKIP_COUNT_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.30");
	static final String MSG_ERROR_OUTPORT_BUFF_LENGTH_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.31");
	static final String MSG_ERROR_OUTPORT_WRITE_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.32");
	static final String MSG_ERROR_OUTPORT_READ_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.33");
	static final String MSG_ERROR_INPORT_BUFF_LENGTH_NOT_INTEGER = Messages.getString("DataConnectorCreaterDialog.34");
	static final String MSG_ERROR_INPORT_WRITE_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.35");
	static final String MSG_ERROR_INPORT_READ_TIMEOUT_NOT_NUMERIC = Messages.getString("DataConnectorCreaterDialog.36");

	private Text nameText;

	private Combo dataTypeCombo;

	private Combo interfaceTypeCombo;

	private Combo dataflowTypeCombo;

	private Combo subscriptionTypeCombo;

	private Text pushRateText;

	private Combo pushPolicyCombo;

	private Text skipCountText;

	Composite detailComposite;

	Point defaultDialogSize;

	private ConnectorProfile connectorProfile;

	private ConnectorProfile dialogResult;

	private OutPort outport;

	private InPort inport;

	BufferPackage ob;

	BufferPackage ib;

	boolean disableNotify;

	static class BufferPackage {
		Text lengthText;
		Combo fullPolicyCombo;
		Text writeTimeoutText;
		Combo emptyPolicyCombo;
		Text readTimeoutText;
	}

	public DataConnectorCreaterDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.CENTER | SWT.RESIZE);
	}

	/**
	 * ConnectorProfileCreater�C���^�t�F�[�X�̎������\�b�h
	 * <p>
	 * ConnectorProfile�ƂȂ��₪��������ꍇ�ɂ́A�_�C�A���O��\�����AConnectorProfile���쐬����B
	 */
	public ConnectorProfile getConnectorProfile(OutPort outport, InPort inport) {
		this.outport = outport;
		this.inport = inport;

		connectorProfile = ComponentFactory.eINSTANCE.createConnectorProfile();
		connectorProfile.setName(outport.getNameL() + "_" + inport.getNameL());

		setShellStyle(this.getShellStyle() | SWT.RESIZE);
		open();

		return dialogResult;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		GridLayout gl;
		GridData gd;

		disableNotify = false;

		Composite mainComposite = (Composite) super.createDialogArea(parent);
		gl = new GridLayout();
		gd = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayout(gl);
		mainComposite.setLayoutData(gd);

		Label label = createLabel(mainComposite, Messages
				.getString("DataConnectorCreaterDialog.1"));
		GridData labelLayloutData = new GridData(
				GridData.HORIZONTAL_ALIGN_BEGINNING);
		label.setLayoutData(labelLayloutData);
		labelLayloutData.heightHint = 20;

		createConnectorProfileComposite(mainComposite);

		return mainComposite;
	}

	/**
	 * ���C���ƂȂ�\�������쐬����
	 */
	private void createConnectorProfileComposite(final Composite mainComposite) {
		GridLayout gl;
		GridData gd;
		Composite portProfileEditComposite = new Composite(mainComposite,
				SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginBottom = 0;
		gl.marginHeight = 0;
		gl.marginLeft = 0;
		gl.marginRight = 0;
		gl.marginTop = 0;
		gl.marginWidth = 0;
		portProfileEditComposite.setLayout(gl);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		portProfileEditComposite.setLayoutData(gd);

		int style;

		Label name = new Label(portProfileEditComposite, SWT.NONE);
		name.setText(Messages.getString("DataConnectorCreaterDialog.2")); //$NON-NLS-1$
		nameText = new Text(portProfileEditComposite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		nameText.setLayoutData(gd);
		nameText.setTextLimit(255);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setName(nameText.getText());
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		Label dataTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.3")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataTypeCombo.setLayoutData(gd);
		dataTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataType(dataTypeCombo.getText());
				notifyModified();
			}
		});
		Label dataTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataTypeFooterLabel.setText(ConnectorUtil.isAllowAnyDataType(
				outport, inport) ? Messages.getString("DataConnectorCreaterDialog.4") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label interfaceTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		interfaceTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.6")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyInterfaceType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		interfaceTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		interfaceTypeCombo.setLayoutData(gd);
		interfaceTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setInterfaceType(interfaceTypeCombo.getText());
				notifyModified();
			}
		});
		Label interfaceTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		interfaceTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyInterfaceType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.7") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label dataflowTypeLabel = new Label(portProfileEditComposite, SWT.NONE);
		dataflowTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.9")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnyDataflowType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		dataflowTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		dataflowTypeCombo.setLayoutData(gd);
		dataflowTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setDataflowType(dataflowTypeCombo.getText());
				if (!connectorProfile.isSubscriptionTypeAvailable()) {
					subscriptionTypeCombo.select(0);
				}
				notifyModified();
			}
		});
		Label dataflowTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		dataflowTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnyDataflowType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.10") : ""); //$NON-NLS-1$ //$NON-NLS-2$

		Label subscriptionTypeLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeLabel.setText(Messages.getString("DataConnectorCreaterDialog.12")); //$NON-NLS-1$
		style = ConnectorUtil.isAllowAnySubscriptionType(outport, inport) ? SWT.DROP_DOWN
				: SWT.DROP_DOWN | SWT.READ_ONLY;
		subscriptionTypeCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		subscriptionTypeCombo.setLayoutData(gd);
		subscriptionTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setSubscriptionType(subscriptionTypeCombo
						.getText());
				if (!connectorProfile.isPushIntervalAvailable()) {
					pushRateText.setText("");
				} else {
					if (pushRateText.getText().isEmpty()) {
						pushRateText.setText("1000.0");
					}
				}
				if (!connectorProfile.isPushPolicyAvailable()) {
					pushPolicyCombo.select(0);
				}
				notifyModified();
			}
		});
		Label subscriptionTypeFooterLabel = new Label(portProfileEditComposite,
				SWT.NONE);
		subscriptionTypeFooterLabel.setText(ConnectorUtil
				.isAllowAnySubscriptionType(outport, inport) ? Messages.getString("DataConnectorCreaterDialog.13") : ""); //$NON-NLS-1$ //$NON-NLS-2$
		subscriptionTypeCombo.setEnabled(false);

		Label pushRate = new Label(portProfileEditComposite, SWT.NONE);
		pushRate.setText(Messages.getString("DataConnectorCreaterDialog.15")); //$NON-NLS-1$
		pushRateText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		pushRateText.setEnabled(false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pushRateText.setLayoutData(gd);
		pushRateText.setTextLimit(9);
		pushRateText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = pushRateText.getText();

				boolean isDouble = false;
				try {
					Double.parseDouble(text);
					isDouble = true;
				} catch (Exception ex) {
					// void
				}

				if (isDouble) {
					connectorProfile.setPushRate(Double.parseDouble(text));
				}

				notifyModified();
			}
		});
		Label footerLabel = new Label(portProfileEditComposite, SWT.NONE);
		footerLabel.setText("");

		// Push Policy
		createLabel(portProfileEditComposite, LABEL_PUSH_POLICY);
		style = SWT.DROP_DOWN | SWT.READ_ONLY;
		pushPolicyCombo = new Combo(portProfileEditComposite, style);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pushPolicyCombo.setLayoutData(gd);
		pushPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				connectorProfile.setPushPolicy(pushPolicyCombo.getText());
				if (!connectorProfile.isSkipCountAvailable()) {
					skipCountText.setText("");
				} else {
					if (skipCountText.getText().isEmpty()) {
						skipCountText.setText("0");
					}
				}

				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		// Skip Count
		createLabel(portProfileEditComposite, LABEL_SKIP_COUNT);
		skipCountText = new Text(portProfileEditComposite, SWT.SINGLE
				| SWT.BORDER);
		skipCountText.setEnabled(false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		skipCountText.setLayoutData(gd);
		skipCountText.setTextLimit(9);
		skipCountText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = skipCountText.getText();
				try {
					int i = Integer.parseInt(text);
					connectorProfile.setSkipCount(i);
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(portProfileEditComposite, "");

		final Button detailCheck = new Button(portProfileEditComposite,
				SWT.CHECK);
		detailCheck.setText(LABEL_DETAIL);
		detailCheck.setSelection(false);
		createLabel(portProfileEditComposite, "");
		createLabel(portProfileEditComposite, "");

		detailCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selection = detailCheck.getSelection();
				if (selection && detailComposite == null) {
					disableNotify = true;
					createDetailComposite(mainComposite);
					disableNotify = false;
				}
				detailComposite.setVisible(selection);
				if (!selection) {
					// �ڍ׃`�F�b�N�������ɁA���̃_�C�A���O�̃T�C�Y�ɖ߂�
					getShell().setSize(defaultDialogSize);
				} else {
					getShell().setSize(
							getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));
				}
			}
		});

		loadData();
	}

	/**
	 * �ڍאݒ�̕\�������쐬����
	 */
	Composite createDetailComposite(Composite parent) {
		GridLayout gl;
		GridData gd;

		detailComposite = new Composite(parent, SWT.NONE);
		gl = new GridLayout(2, false);
		gd = new GridData(GridData.FILL_BOTH);
		detailComposite.setLayout(gl);
		detailComposite.setLayoutData(gd);
		detailComposite.setVisible(false);

		ob = new BufferPackage();
		createBufferComposite(detailComposite, LABEL_OUTPORT_BUFFER, ob);

		ib = new BufferPackage();
		createBufferComposite(detailComposite, LABEL_INPORT_BUFFER, ib);

		loadDetailData();

		defaultDialogSize = getShell().getSize();
		getShell().setSize(getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT));

		return detailComposite;
	}

	/**
	 * �|�[�g�o�b�t�@�ݒ�̕\�������쐬����
	 */
	Composite createBufferComposite(Composite parent, String label,
			BufferPackage pkg) {
		GridLayout gl;
		GridData gd;

		Group composite = new Group(parent, SWT.NONE);
		gl = new GridLayout(3, false);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		composite.setLayout(gl);
		composite.setLayoutData(gd);
		composite.setText(label);

		final boolean isOutport = (pkg == ob);

		// Buffer length
		createLabel(composite, LABEL_BUFFER_LENGTH);
		pkg.lengthText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.lengthText.setLayoutData(gd);
		pkg.lengthText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.lengthText.getText();
				try {
					int i = Integer.parseInt(text);
					if (isOutport) {
						connectorProfile.setOutportBufferLength(i);
					} else {
						connectorProfile.setInportBufferLength(i);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer full policy
		createLabel(composite, LABEL_BUFFER_FULL_POLICY);
		pkg.fullPolicyCombo = new Combo(composite, SWT.DROP_DOWN
				| SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.fullPolicyCombo.setLayoutData(gd);
		pkg.fullPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String value = bp.fullPolicyCombo.getText();
				if (isOutport) {
					connectorProfile.setOutportBufferFullPolicy(value);
				} else {
					connectorProfile.setInportBufferFullPolicy(value);
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer write timeout
		createLabel(composite, LABEL_BUFFER_WRITE_TIMEOUT);
		pkg.writeTimeoutText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.writeTimeoutText.setLayoutData(gd);
		pkg.writeTimeoutText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.writeTimeoutText.getText();
				try {
					double d = Double.parseDouble(text);
					if (isOutport) {
						connectorProfile.setOutportBufferWriteTimeout(d);
					} else {
						connectorProfile.setInportBufferWriteTimeout(d);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer empty policy
		createLabel(composite, LABEL_BUFFER_EMPTY_POLICY);
		pkg.emptyPolicyCombo = new Combo(composite, SWT.DROP_DOWN
				| SWT.READ_ONLY);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.emptyPolicyCombo.setLayoutData(gd);
		pkg.emptyPolicyCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String value = bp.emptyPolicyCombo.getText();
				if (isOutport) {
					connectorProfile.setOutportBufferEmptyPolicy(value);
				} else {
					connectorProfile.setInportBufferEmptyPolicy(value);
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		// Buffer read timeout
		createLabel(composite, LABEL_BUFFER_READ_TIMEOUT);
		pkg.readTimeoutText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gd = new GridData();
		gd.horizontalAlignment = GridData.FILL;
		gd.grabExcessHorizontalSpace = true;
		pkg.readTimeoutText.setLayoutData(gd);
		pkg.readTimeoutText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				BufferPackage bp = (isOutport) ? ob : ib;
				String text = bp.readTimeoutText.getText();
				try {
					double d = Double.parseDouble(text);
					if (isOutport) {
						connectorProfile.setOutportBufferReadTimeout(d);
					} else {
						connectorProfile.setInportBufferReadTimeout(d);
					}
				} catch (Exception ex) {
					// void
				}
				notifyModified();
			}
		});
		createLabel(composite, "");

		return composite;
	}

	Label createLabel(Composite parent, String label) {
		Label l = new Label(parent, SWT.NONE);
		l.setText(label);
		return l;
	}

	/**
	 * ���f�����ɃA�N�Z�X���A�\���ɐݒ肷��
	 */
	void loadData() {
		//
		nameText.setText(connectorProfile.getName());
		//
		List<String> types = ConnectorUtil.getAllowDataTypes(outport, inport);
		boolean isAllowAny = ConnectorUtil.isAllowAnyDataType(outport, inport);
		String value = loadCombo(dataTypeCombo, types, connectorProfile
				.getDataType(), isAllowAny);
		connectorProfile.setDataType(value);

		boolean isOffline = inport.eContainer() instanceof ComponentSpecification;

		SystemEditorPreferenceManager preference = SystemEditorPreferenceManager
				.getInstance();

		//
		if (!isOffline) {
			types = ConnectorUtil.getAllowInterfaceTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnyInterfaceType(outport, inport);
		} else {
			types = Arrays.asList(preference.getInterfaceTypes());
			isAllowAny = false;
		}
		value = loadCombo(interfaceTypeCombo, types, connectorProfile
				.getInterfaceType(), isAllowAny);
		connectorProfile.setInterfaceType(value);
		//
		if (!isOffline) {
			types = ConnectorUtil.getAllowDataflowTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnyDataflowType(outport, inport);
		} else {
			types = Arrays.asList(preference.getDataFlowTypes());
			isAllowAny = false;
		}
		value = loadCombo(dataflowTypeCombo, types, connectorProfile
				.getDataflowType(), isAllowAny);
		connectorProfile.setDataflowType(value);
		//
		if (!isOffline) {
			types = ConnectorUtil.getAllowSubscriptionTypes(outport, inport);
			isAllowAny = ConnectorUtil.isAllowAnySubscriptionType(outport,
					inport);
		} else {
			types = Arrays.asList(preference.getSubscriptionTypes());
			isAllowAny = false;
		}
		value = loadCombo(subscriptionTypeCombo, types, connectorProfile
				.getSubscriptionType(), isAllowAny);
		connectorProfile.setSubscriptionType(value);
		//
		if (!isOffline) {
			types = Arrays.asList(ConnectorProfile.PUSH_POLICY_TYPES);
		} else {
			types = Arrays.asList(preference.getPushPolicies());
		}
		isAllowAny = false;
		value = loadCombo(pushPolicyCombo, types, connectorProfile
				.getPushPolicy(), isAllowAny);
		connectorProfile.setPushPolicy(value);

		loadDetailData();
	}

	/**
	 * ���f���̏ڍאݒ荀�ڂ�\������
	 */
	void loadDetailData() {
		List<String> fullTypes;
		List<String> emptyTypes;
		String value;
		boolean isAllowAny = false;

		boolean isOffline = inport.eContainer() instanceof ComponentSpecification;

		SystemEditorPreferenceManager preference = SystemEditorPreferenceManager
				.getInstance();

		if (!isOffline) {
			fullTypes = Arrays
					.asList(ConnectorProfile.BUFFER_FULL_POLICY_TYPES);
			emptyTypes = Arrays
					.asList(ConnectorProfile.BUFFER_EMPTY_POLICY_TYPES);
		} else {
			fullTypes = Arrays.asList(preference.getBufferFullPolicies());
			emptyTypes = Arrays.asList(preference.getBufferEmptyPolicies());
		}

		if (ob != null) {
			//
			value = loadCombo(ob.fullPolicyCombo, fullTypes, connectorProfile
					.getOutportBufferFullPolicy(), isAllowAny);
			connectorProfile.setOutportBufferFullPolicy(value);
			//
			value = loadCombo(ob.emptyPolicyCombo, emptyTypes, connectorProfile
					.getOutportBufferEmptyPolicy(), isAllowAny);
			connectorProfile.setOutportBufferEmptyPolicy(value);
			//
			if (connectorProfile.getOutportBufferLength() == null) {
				connectorProfile.setOutportBufferLength(0);
			}
			value = connectorProfile.getOutportBufferLength().toString();
			ob.lengthText.setText(value);
			//
			if (connectorProfile.getOutportBufferWriteTimeout() == null) {
				connectorProfile.setOutportBufferWriteTimeout(1.0);
			}
			value = connectorProfile.getOutportBufferWriteTimeout().toString();
			ob.writeTimeoutText.setText(value);
			//
			if (connectorProfile.getOutportBufferReadTimeout() == null) {
				connectorProfile.setOutportBufferReadTimeout(1.0);
			}
			value = connectorProfile.getOutportBufferReadTimeout().toString();
			ob.readTimeoutText.setText(value);
		}
		//
		if (ib != null) {
			//
			value = loadCombo(ib.fullPolicyCombo, fullTypes, connectorProfile
					.getInportBufferFullPolicy(), isAllowAny);
			connectorProfile.setInportBufferFullPolicy(value);
			//
			value = loadCombo(ib.emptyPolicyCombo, emptyTypes, connectorProfile
					.getInportBufferEmptyPolicy(), isAllowAny);
			connectorProfile.setInportBufferEmptyPolicy(value);
			//
			if (connectorProfile.getInportBufferLength() == null) {
				connectorProfile.setInportBufferLength(0);
			}
			value = connectorProfile.getInportBufferLength().toString();
			ib.lengthText.setText(value);
			//
			if (connectorProfile.getInportBufferWriteTimeout() == null) {
				connectorProfile.setInportBufferWriteTimeout(1.0);
			}
			value = connectorProfile.getInportBufferWriteTimeout().toString();
			ib.writeTimeoutText.setText(value);
			//
			if (connectorProfile.getInportBufferReadTimeout() == null) {
				connectorProfile.setInportBufferReadTimeout(1.0);
			}
			value = connectorProfile.getInportBufferReadTimeout().toString();
			ib.readTimeoutText.setText(value);
		}
	}

	String loadCombo(Combo combo, List<String> types, String value,
			boolean isAllowAny) {
		combo.setItems(types.toArray(new String[0]));
		String def = getDefaultValue(types, value, isAllowAny);
		int index = types.indexOf(def);
		index = (index == -1) ? 0 : index;
		combo.select(index);
		return types.get(index);
	}

	/**
	 * �R���{�ɂ����āA�u�\�����̃��X�g�v�ƁA�u�ǂ̂悤�ȕ�����ł��ݒ�\�ł��邩�ǂ����v�������Ɏ��A�����\���̕���������肷��
	 * 
	 * @param candidateList
	 *            �\����⃊�X�g
	 * @param isAllowAny
	 *            �ǂ̂悤�ȕ�����ł��ݒ�\�ł��邩�ǂ���
	 * @return �����\���̕�����
	 */
	private String getDefaultValue(List<String> candidateList, String value,
			boolean isAllowAny) {
		String result = null;
		if (candidateList.size() > 0) {
			if (candidateList.contains(value)) {
				result = value;
			} else {
				result = candidateList.get(0);
			}
		}
		if (result == null) {
			if (isAllowAny) {
				result = ConnectorProfile.ANY;
			}
		}
		return result;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("DataConnectorCreaterDialog.16")); //$NON-NLS-1$
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	protected void okPressed() {
		dialogResult = connectorProfile;
		super.okPressed();
	}

	@Override
	/**
	 * {@inheritDoc}
	 * <p>
	 * ���b�Z�[�W��ݒ肷��B ���b�Z�[�W�Ƃ��Ă̓G���[���b�Z�[�W��z�肵�Ă���A
	 * �G���[���b�Z�[�W�����݂��邩�󕶎����ǂ����ɂ��AOK�{�^����Enable�̐�����s���悤�ɁA�I�[�o�[���C�h�����B
	 */
	public void setMessage(String newMessage, int newType) {
		super.setMessage(newMessage, newType);

		boolean isOkEnable = false;
		if (newMessage.length() == 0) {
			isOkEnable = true;
		}

		getButton(IDialogConstants.OK_ID).setEnabled(isOkEnable);
	}

	/**
	 * �ݒ�ɕύX���������ꍇ�ɌĂяo����邱�Ƃ�z�肵�����\�b�h�B
	 * SubscriptionType�R���{��PushInterbal��Enable���Ǘ�����B
	 * <p>
	 * ���ӁF�ݒ�l�̕ύX������ꍇ�ɂ́A�K�����̃��\�b�h���Ăяo������<br>
	 * ���݂́A�\�����Őݒ��ύX������ɁA���̃��\�b�h��K���Ăяo���悤�Ɏ������Ă��邪�A
	 * ���ڐ���������悤�Ȃ�΁A���f���̕ύX�ʒm�@�\���g�p���Ď�����������ǂ��B
	 */
	public void notifyModified() {
		if (disableNotify) {
			return;
		}
		if (getButton(IDialogConstants.OK_ID) != null) {
			setMessage("", IMessageProvider.NONE); //$NON-NLS-1$
		}

		if (connectorProfile.isPushIntervalAvailable()) {
			boolean isDouble = false;
			try {
				double value = Double.parseDouble(pushRateText.getText());
				if (value > 0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}

			if (!isDouble) {
				setMessage(MSG_ERROR_PUSH_RATE_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		if (connectorProfile.isSkipCountAvailable()) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(skipCountText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_SKIP_COUNT_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
		}

		if (ob != null) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(ob.lengthText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_OUTPORT_BUFF_LENGTH_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
			//
			boolean isDouble = false;
			try {
				double d = Double.parseDouble(ob.writeTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_OUTPORT_WRITE_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
			//
			isDouble = false;
			try {
				double d = Double.parseDouble(ob.readTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_OUTPORT_READ_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		if (ib != null) {
			boolean isInt = false;
			try {
				int i = Integer.parseInt(ib.lengthText.getText());
				if (i >= 0) {
					isInt = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isInt) {
				setMessage(MSG_ERROR_INPORT_BUFF_LENGTH_NOT_INTEGER,
						IMessageProvider.ERROR);
			}
			//
			boolean isDouble = false;
			try {
				double d = Double.parseDouble(ib.writeTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_INPORT_WRITE_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
			//
			isDouble = false;
			try {
				double d = Double.parseDouble(ib.readTimeoutText.getText());
				if (d >= 0.0) {
					isDouble = true;
				}
			} catch (Exception ex) {
				// void
			}
			if (!isDouble) {
				setMessage(MSG_ERROR_INPORT_READ_TIMEOUT_NOT_NUMERIC,
						IMessageProvider.ERROR);
			}
		}

		subscriptionTypeCombo.setEnabled(connectorProfile
				.isSubscriptionTypeAvailable());

		pushRateText.setEnabled(connectorProfile.isPushIntervalAvailable());

		pushPolicyCombo.setEnabled(connectorProfile.isPushPolicyAvailable());

		skipCountText.setEnabled(connectorProfile.isSkipCountAvailable());
	}

	@Override
	protected Point getInitialSize() {
		return getShell().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
	}

}