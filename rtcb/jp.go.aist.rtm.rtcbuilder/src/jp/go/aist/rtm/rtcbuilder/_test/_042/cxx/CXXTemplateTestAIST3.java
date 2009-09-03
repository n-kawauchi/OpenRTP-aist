package jp.go.aist.rtm.rtcbuilder._test._042.cxx;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.DataPortParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortInterfaceParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.ServicePortParam;

public class CXXTemplateTestAIST3 extends TestBase {

	protected void setUp() throws Exception {
	}

	public void testAIST8() throws Exception{
		String resourceDir = "CXX\\module";
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam,true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("0.4.2");
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.setInports(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedDouble", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedFloat", "", 0));
		rtcParam.setOutports(outport);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "resource\\042\\CXX\\AIST7\\Service1.idl", "test", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "resource\\042\\CXX\\AIST7\\";
//		checkCode(result, targetDir, "fooComp.cpp");
//		checkCode(result, targetDir, "Makefile.foo");
//		checkCode(result, targetDir, "foo.h");
//		checkCode(result, targetDir, "foo.cpp");
//		checkCode(result, targetDir, "README.foo");
//		//
//		checkCode(result, targetDir, "foo_vc8.sln");
//		checkCode(result, targetDir, "foo_vc8.vcproj");
//		checkCode(result, targetDir, "fooComp_vc8.vcproj");
//		checkCode(result, targetDir, "foo_vc9.sln");
//		checkCode(result, targetDir, "foo_vc9.vcproj");
//		checkCode(result, targetDir, "fooComp_vc9.vcproj");
//		//
//		checkCode(result, targetDir, "copyprops.bat");
//		checkCode(result, targetDir, "user_config.vsprops");
		//
		checkCode(result, targetDir, "Service1SVC_impl.h");
		checkCode(result, targetDir, "Service1SVC_impl.cpp");
	}

	public void testAIST7() throws Exception{
		String resourceDir = "CXX\\module";
		GeneratorParam genParam = new GeneratorParam();
		RtcParam rtcParam = new RtcParam(genParam,true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.1");
		rtcParam.setVender("TA");
		rtcParam.setCategory("Manip");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setMaxInstance(5);
		rtcParam.setRtmVersion("0.4.2");
		rtcParam.setIsTest(true);

		List<DataPortParam> dataport = new ArrayList<DataPortParam>(); 
		dataport.add(new DataPortParam("InP1", "TimedShort", "", 0));
		dataport.add(new DataPortParam("InP2", "TimedLong", "", 0));
		rtcParam.setInports(dataport);
		List<DataPortParam> outport = new ArrayList<DataPortParam>(); 
		outport.add(new DataPortParam("OutP1", "TimedDouble", "", 0));
		outport.add(new DataPortParam("OutP2", "TimedFloat", "", 0));
		rtcParam.setOutports(outport);

		ServicePortParam service1 = new ServicePortParam("svPort",0);
		List<ServicePortInterfaceParam> srvinterts = new ArrayList<ServicePortInterfaceParam>(); 
		ServicePortInterfaceParam int1 = new ServicePortInterfaceParam(service1, "acc", "", "", 
				rootPath + "resource\\042\\CXX\\AIST6\\Service1.idl", "test", "", 0);
		srvinterts.add(int1);
		service1.setServicePortInterfaces(srvinterts);
		List<ServicePortParam> srvports = new ArrayList<ServicePortParam>();
		srvports.add(service1);
		rtcParam.setServicePorts(srvports);
		genParam.getRtcParams().add(rtcParam);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String targetDir = rootPath + "resource\\042\\CXX\\AIST6\\";
		checkCode(result, targetDir, "fooComp.cpp");
		checkCode(result, targetDir, "Makefile.foo");
		checkCode(result, targetDir, "foo.h");
		checkCode(result, targetDir, "foo.cpp");
		checkCode(result, targetDir, "README.foo");
		//
		checkCode(result, targetDir, "foo_vc8.sln");
		checkCode(result, targetDir, "foo_vc8.vcproj");
		checkCode(result, targetDir, "fooComp_vc8.vcproj");
		checkCode(result, targetDir, "foo_vc9.sln");
		checkCode(result, targetDir, "foo_vc9.vcproj");
		checkCode(result, targetDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, targetDir, "copyprops.bat");
		checkCode(result, targetDir, "user_config.vsprops");
		//
		checkCode(result, targetDir, "Service1SVC_impl.h");
		checkCode(result, targetDir, "Service1SVC_impl.cpp");
	}
}
