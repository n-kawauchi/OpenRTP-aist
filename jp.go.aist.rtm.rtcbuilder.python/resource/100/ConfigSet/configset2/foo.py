#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
"""
 @file foo.py
 @brief MDesc
 @date $Date$
"""
import sys
import time
sys.path.append(".")
# Import RTM module
import RTC
import OpenRTM_aist
# Import Service implementation class
# <rtc-template block="service_impl">
# </rtc-template>
# Import Service stub modules
# <rtc-template block="consumer_import">
# </rtc-template>
# This module's spesification
# <rtc-template block="module_spec">
foo_spec = ["implementation_id", "foo", 
         "type_name",         "foo", 
         "description",       "MDesc", 
         "version",           "1.0.3", 
         "vendor",            "TA2", 
         "category",          "manip2", 
         "activity_type",     "STATIC2", 
         "max_instance",      "3", 
         "language",          "Python", 
         "lang_type",         "SCRIPT",
         "conf.default.int_param0", "0",
         "conf.default.int_param1", "1",
         "conf.__type__.int_param0", "int",
         "conf.__type__.int_param1", "int",
         ""]
# </rtc-template>
##
# @class foo
# @brief MDesc
# 
# 
class foo(OpenRTM_aist.DataFlowComponentBase):
	
    ##
    # @brief constructor
    # @param manager Maneger Object
    # 
    def __init__(self, manager):
        OpenRTM_aist.DataFlowComponentBase.__init__(self, manager)
		
        # initialize of configuration-data.
        # <rtc-template block="init_conf_param">
        """
        
         - Name:  int_param0
         - DefaultValue: 0
        """
        self._int_param0 = [0]
        """
        
         - Name:  int_param1
         - DefaultValue: 1
        """
        self._int_param1 = [1]
		
        # </rtc-template>
		 
    ##
    #
    # The initialize action (on CREATED->ALIVE transition)
    # 
    # @return RTC::ReturnCode_t
    # 
    #
    def onInitialize(self):
        # Bind variables and configuration variable
        self.bindParameter("int_param0", self._int_param0, "0")
        self.bindParameter("int_param1", self._int_param1, "1")
		
        # Set InPort buffers
		
        # Set OutPort buffers
		
        # Set service provider to Ports
		
        # Set service consumers to Ports
		
        # Set CORBA Service Ports
		
        return RTC.RTC_OK
	
    ###
    ## 
    ## The finalize action (on ALIVE->END transition)
    ## 
    ## @return RTC::ReturnCode_t
    #
    ## 
    #def onFinalize(self):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The startup action when ExecutionContext startup
    ## 
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onStartup(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The shutdown action when ExecutionContext stop
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onShutdown(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The activated action (Active state entry action)
    ##
    ## @param ec_id target ExecutionContext Id
    ## 
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onActivated(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The deactivated action (Active state exit action)
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onDeactivated(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The execution action that is invoked periodically
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onExecute(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The aborting action when main logic error occurred.
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onAborting(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The error action in ERROR state
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onError(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The reset action that is invoked resetting
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onReset(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The state update action that is invoked after onExecute() action
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onStateUpdate(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The action that is invoked when execution context's rate is changed
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onRateChanged(self, ec_id):
    #
    #    return RTC.RTC_OK
	
def fooInit(manager):
    profile = OpenRTM_aist.Properties(defaults_str=foo_spec)
    manager.registerFactory(profile,
                            foo,
                            OpenRTM_aist.Delete)
def MyModuleInit(manager):
    fooInit(manager)
    # create instance_name option for createComponent()
    instance_name = [i for i in sys.argv if "--instance_name=" in i]
    if instance_name:
        args = instance_name[0].replace("--", "?")
    else:
        args = ""
  
    # Create a component
    comp = manager.createComponent("foo" + args)
def main():
    # remove --instance_name= option
    argv = [i for i in sys.argv if not "--instance_name=" in i]
    # Initialize manager
    mgr = OpenRTM_aist.Manager.init(sys.argv)
    mgr.setModuleInitProc(MyModuleInit)
    mgr.activateManager()
    mgr.runManager()
if __name__ == "__main__":
    main()
