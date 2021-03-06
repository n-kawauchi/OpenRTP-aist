/**
 * <copyright>
 * </copyright>
 *
 * $Id: CorePackageImpl.java,v 1.4 2008/03/06 04:01:49 yamashita Exp $
 */
package jp.go.aist.rtm.toolscommon.model.core.impl;

import jp.go.aist.rtm.toolscommon.model.component.ComponentPackage;
import jp.go.aist.rtm.toolscommon.model.component.impl.ComponentPackageImpl;
import jp.go.aist.rtm.toolscommon.model.core.CorbaWrapperObject;
import jp.go.aist.rtm.toolscommon.model.core.CoreFactory;
import jp.go.aist.rtm.toolscommon.model.core.CorePackage;
import jp.go.aist.rtm.toolscommon.model.core.ModelElement;
import jp.go.aist.rtm.toolscommon.model.core.Point;
import jp.go.aist.rtm.toolscommon.model.core.Rectangle;
import jp.go.aist.rtm.toolscommon.model.core.Visiter;
import jp.go.aist.rtm.toolscommon.model.core.WrapperObject;
import jp.go.aist.rtm.toolscommon.model.manager.ManagerPackage;
import jp.go.aist.rtm.toolscommon.model.manager.impl.ManagerPackageImpl;
import jp.go.aist.rtm.toolscommon.synchronizationframework.LocalObject;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaWrapperObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iAdaptableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass localObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapperObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rectangleEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType visiterEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType pointEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see jp.go.aist.rtm.toolscommon.model.core.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CorePackage init() {
		if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Obtain or create and register package
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		ManagerPackageImpl theManagerPackage = (ManagerPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) instanceof ManagerPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ManagerPackage.eNS_URI) : ManagerPackage.eINSTANCE);
		ComponentPackageImpl theComponentPackage = (ComponentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) instanceof ComponentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentPackage.eNS_URI) : ComponentPackage.eINSTANCE);

		// Create package meta-data objects
		theCorePackage.createPackageContents();
		theManagerPackage.createPackageContents();
		theComponentPackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();
		theManagerPackage.initializePackageContents();
		theComponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorbaWrapperObject() {
		return corbaWrapperObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCorbaWrapperObject_CorbaObject() {
		return (EAttribute)corbaWrapperObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelElement_Constraint() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIAdaptable() {
		return iAdaptableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocalObject() {
		return localObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWrapperObject() {
		return wrapperObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRectangle() {
		return rectangleEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getVisiter() {
		return visiterEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPoint() {
		return pointEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactory getCoreFactory() {
		return (CoreFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		corbaWrapperObjectEClass = createEClass(CORBA_WRAPPER_OBJECT);
		createEAttribute(corbaWrapperObjectEClass, CORBA_WRAPPER_OBJECT__CORBA_OBJECT);

		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__CONSTRAINT);

		iAdaptableEClass = createEClass(IADAPTABLE);

		localObjectEClass = createEClass(LOCAL_OBJECT);

		wrapperObjectEClass = createEClass(WRAPPER_OBJECT);

		// Create data types
		rectangleEDataType = createEDataType(RECTANGLE);
		visiterEDataType = createEDataType(VISITER);
		objectEDataType = createEDataType(OBJECT);
		pointEDataType = createEDataType(POINT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		corbaWrapperObjectEClass.getESuperTypes().add(this.getWrapperObject());
		modelElementEClass.getESuperTypes().add(this.getIAdaptable());
		wrapperObjectEClass.getESuperTypes().add(this.getModelElement());
		wrapperObjectEClass.getESuperTypes().add(this.getLocalObject());

		// Initialize classes and features; add operations and parameters
		initEClass(corbaWrapperObjectEClass, CorbaWrapperObject.class, "CorbaWrapperObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaWrapperObject_CorbaObject(), this.getObject(), "corbaObject", null, 0, 1, CorbaWrapperObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelElementEClass, ModelElement.class, "ModelElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Constraint(), this.getRectangle(), "constraint", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(modelElementEClass, null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getVisiter(), "visiter", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iAdaptableEClass, IAdaptable.class, "IAdaptable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(localObjectEClass, LocalObject.class, "LocalObject", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapperObjectEClass, WrapperObject.class, "WrapperObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(rectangleEDataType, Rectangle.class, "Rectangle", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(visiterEDataType, Visiter.class, "Visiter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectEDataType, org.omg.CORBA.Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(pointEDataType, Point.class, "Point", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //CorePackageImpl
