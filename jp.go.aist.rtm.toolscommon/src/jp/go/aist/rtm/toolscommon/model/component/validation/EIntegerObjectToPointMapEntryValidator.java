package jp.go.aist.rtm.toolscommon.model.component.validation;


import jp.go.aist.rtm.toolscommon.model.core.Point;

/**
 * A sample validator interface for {@link java.util.Map.Entry}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface EIntegerObjectToPointMapEntryValidator {
	boolean validate();

	boolean validateTypedKey(Integer value);
	boolean validateTypedValue(Point value);
}