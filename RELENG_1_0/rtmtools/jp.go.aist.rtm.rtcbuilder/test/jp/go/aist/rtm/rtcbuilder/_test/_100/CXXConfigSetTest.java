package jp.go.aist.rtm.rtcbuilder._test._100;

import java.util.ArrayList;
import java.util.List;

import jp.go.aist.rtm.rtcbuilder.Generator;
import jp.go.aist.rtm.rtcbuilder.IRtcBuilderConstants;
import jp.go.aist.rtm.rtcbuilder._test.TestBase;
import jp.go.aist.rtm.rtcbuilder.generator.GeneratedResult;
import jp.go.aist.rtm.rtcbuilder.generator.param.ConfigSetParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.GeneratorParam;
import jp.go.aist.rtm.rtcbuilder.generator.param.RtcParam;

public class CXXConfigSetTest extends TestBase {
	private GeneratorParam genParam;
	private RtcParam rtcParam;

	protected void setUp() throws Exception {
		genParam = new GeneratorParam();
		rtcParam = new RtcParam(genParam, true);
		rtcParam.setOutputProject(rootPath + "\\resource\\work");
		rtcParam.setLanguage(IRtcBuilderConstants.LANG_CPP);
		rtcParam.setLanguageArg(IRtcBuilderConstants.LANG_CPP_ARG);
		rtcParam.setName("foo");
		rtcParam.setDescription("MDesc");
		rtcParam.setVersion("1.0.3");
		rtcParam.setVender("TA2");
		rtcParam.setCategory("manip2");
		rtcParam.setComponentType("STATIC2");
		rtcParam.setActivityType("PERIODIC2");
		rtcParam.setComponentKind("DataFlowComponent");
		rtcParam.setMaxInstance(3);
		rtcParam.setRtmVersion("1.0.0");
		rtcParam.setIsTest(true);
		genParam.getRtcParams().add(rtcParam);
	}

	public void testConfigSet() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\ConfigSet\\configset1\\";
		checkResults(result, resourceDir);
	}

	public void testConfigSet2() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\ConfigSet\\configset2\\";
		checkResults(result, resourceDir);
	}

	public void testConfigSet3() throws Exception{
		List<ConfigSetParam> configset = new ArrayList<ConfigSetParam>(); 
		configset.add(new ConfigSetParam("int_param0","int","", "0"));
		configset.add(new ConfigSetParam("int_param1","int","", "1"));
		configset.add(new ConfigSetParam("double_param0","double","", "0.11"));
		configset.add(new ConfigSetParam("str_param0","std::string","", "hoge"));
		configset.add(new ConfigSetParam("str_param1","std::string","", "dara"));
		rtcParam.getConfigParams().addAll(configset);

		Generator generator = new Generator();
		List<GeneratedResult> result = generator.generateTemplateCode(genParam);

		String resourceDir = rootPath +  "\\resource\\100\\CXX\\ConfigSet\\configset3\\";
		checkResults(result, resourceDir);
	}
	
	private void checkResults(List<GeneratedResult> result, String resourceDir) {
		assertEquals(13, result.size());
		checkCode(result, resourceDir, "fooComp.cpp");
		checkCode(result, resourceDir, "Makefile.foo");
		checkCode(result, resourceDir, "foo.h");
		checkCode(result, resourceDir, "foo.cpp");
		try {
			checkCode(result, resourceDir, "README.foo");
			fail();
		} catch(Exception ex) {
		}
		//
		checkCode(result, resourceDir, "foo_vc8.sln");
		checkCode(result, resourceDir, "foo_vc8.vcproj");
		checkCode(result, resourceDir, "foo_vc9.sln");
		checkCode(result, resourceDir, "foo_vc9.vcproj");
		checkCode(result, resourceDir, "fooComp_vc8.vcproj");
		checkCode(result, resourceDir, "fooComp_vc9.vcproj");
		//
		checkCode(result, resourceDir, "copyprops.bat");
		checkCode(result, resourceDir, "user_config.vsprops");
	}

}
