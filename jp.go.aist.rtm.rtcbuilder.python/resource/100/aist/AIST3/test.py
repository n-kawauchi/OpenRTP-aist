#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
"""
 @file test.py
 @brief test component
 @date $Date$
"""
import sys
import time
sys.path.append(".")
# Import RTM module
import RTC
import OpenRTM_aist
import MyService_idl
# Import Service implementation class
# <rtc-template block="service_impl">
from MyService_idl_example import *
# </rtc-template>
# Import Service stub modules
# <rtc-template block="consumer_import">
import _GlobalIDL, _GlobalIDL__POA
# </rtc-template>
# This module's spesification
# <rtc-template block="module_spec">
test_spec = ["implementation_id", "test", 
		 "type_name",         "test", 
		 "description",       "test component", 
		 "version",           "1.0.0", 
		 "vendor",            "S.Kurihara", 
		 "category",          "example", 
		 "activity_type",     "STATIC", 
		 "max_instance",      "1", 
		 "language",          "Python", 
		 "lang_type",         "SCRIPT",
		 ""]
# </rtc-template>
##
# @class test
# @brief test component
# 
# 
class test(OpenRTM_aist.DataFlowComponentBase):
	
	##
	# @brief constructor
	# @param manager Maneger Object
	# 
	def __init__(self, manager):
		OpenRTM_aist.DataFlowComponentBase.__init__(self, manager)
		in_arg = [None] * ((len(RTC._d_TimedFloatSeq) - 4) / 2)
		self._d_in = RTC.TimedFloatSeq(*in_arg)
		"""
		"""
		self._inIn = OpenRTM_aist.InPort("in", self._d_in)
		out_arg = [None] * ((len(RTC._d_TimedFloatSeq) - 4) / 2)
		self._d_out = RTC.TimedFloatSeq(*out_arg)
		"""
		"""
		self._outOut = OpenRTM_aist.OutPort("out", self._d_out)
		"""
		"""
		self._MySVProPort = OpenRTM_aist.CorbaPort("MySVPro")
		"""
		"""
		self._MySVConPort = OpenRTM_aist.CorbaPort("MySVCon")
		"""
		"""
		self._myservice0 = MyService_i()
		
		"""
		"""
		self._myservice1 = OpenRTM_aist.CorbaConsumer(interfaceType=_GlobalIDL.MyService)
		# initialize of configuration-data.
		# <rtc-template block="init_conf_param">
		
		# </rtc-template>
		 
	##
	#
	# The initialize action (on CREATED->ALIVE transition)
	# formaer rtc_init_entry() 
	# 
	# @return RTC::ReturnCode_t
	# 
	#
	def onInitialize(self):
		# Bind variables and configuration variable
		
		# Set InPort buffers
		self.addInPort("in",self._inIn)
		
		# Set OutPort buffers
		self.addOutPort("out",self._outOut)
		
		# Set service provider to Ports
		self._MySVProPort.registerProvider("myservice0", "MyService", self._myservice0)
		
		# Set service consumers to Ports
		self._MySVConPort.registerConsumer("myservice1", "MyService", self._myservice1)
		
		# Set CORBA Service Ports
		self.addPort(self._MySVProPort)
		self.addPort(self._MySVConPort)
		
		return RTC.RTC_OK
	
	#	##
	#	# 
	#	# The finalize action (on ALIVE->END transition)
	#	# formaer rtc_exiting_entry()
	#	# 
	#	# @return RTC::ReturnCode_t
	#
	#	# 
	#def onFinalize(self):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The startup action when ExecutionContext startup
	#	# former rtc_starting_entry()
	#	# 
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onStartup(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The shutdown action when ExecutionContext stop
	#	# former rtc_stopping_entry()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onShutdown(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The activated action (Active state entry action)
	#	# former rtc_active_entry()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	# 
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onActivated(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The deactivated action (Active state exit action)
	#	# former rtc_active_exit()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onDeactivated(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The execution action that is invoked periodically
	#	# former rtc_active_do()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onExecute(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The aborting action when main logic error occurred.
	#	# former rtc_aborting_entry()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onAborting(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The error action in ERROR state
	#	# former rtc_error_do()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onError(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The reset action that is invoked resetting
	#	# This is same but different the former rtc_init_entry()
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onReset(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The state update action that is invoked after onExecute() action
	#	# no corresponding operation exists in OpenRTm-aist-0.2.0
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onStateUpdate(self, ec_id):
	#
	#	return RTC.RTC_OK
	
	#	##
	#	#
	#	# The action that is invoked when execution context's rate is changed
	#	# no corresponding operation exists in OpenRTm-aist-0.2.0
	#	#
	#	# @param ec_id target ExecutionContext Id
	#	#
	#	# @return RTC::ReturnCode_t
	#	#
	#	#
	#def onRateChanged(self, ec_id):
	#
	#	return RTC.RTC_OK
	
def testInit(manager):
    profile = OpenRTM_aist.Properties(defaults_str=test_spec)
    manager.registerFactory(profile,
                            test,
                            OpenRTM_aist.Delete)
def MyModuleInit(manager):
    testInit(manager)
    # Create a component
    comp = manager.createComponent("test")
def main():
	mgr = OpenRTM_aist.Manager.init(sys.argv)
	mgr.setModuleInitProc(MyModuleInit)
	mgr.activateManager()
	mgr.runManager()
if __name__ == "__main__":
	main()
