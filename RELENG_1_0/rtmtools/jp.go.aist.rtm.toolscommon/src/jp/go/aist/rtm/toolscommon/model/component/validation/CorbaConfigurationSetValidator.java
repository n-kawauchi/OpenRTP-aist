package jp.go.aist.rtm.toolscommon.model.component.validation;


import _SDOPackage.ConfigurationSet;

/**
 * A sample validator interface for {@link jp.go.aist.rtm.toolscommon.model.component.CorbaConfigurationSet}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CorbaConfigurationSetValidator {
	boolean validate();

	boolean validateSDOConfigurationSet(ConfigurationSet value);
}