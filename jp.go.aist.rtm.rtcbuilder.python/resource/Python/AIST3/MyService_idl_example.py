#!/usr/bin/env python

"""
 \file MyService_idl_examplefile.py
 \brief Python example implementations generated from MyService.idl
 \date $Date$


"""

import CORBA, PortableServer

# Import the Python stub modules so type definitions are available.

import _GlobalIDL

# Import the Python Skeleton modules so skeleton base classes are available.

import _GlobalIDL__POA


class MyService_i (_GlobalIDL__POA.MyService):
    """
    \class MyService_i
    Example class implementing IDL interface MyService
    """

    def __init__(self):
        """
        \brief standard constructor
        Initialise member variables here
        """
        pass

    # string echo(in string msg)
    def echo(self, msg):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result

    # EchoList get_echo_history()
    def get_echo_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result

    # void set_value(in float value)
    def set_value(self, value):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: None

    # float get_value()
    def get_value(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result

    # ValueList get_value_history()
    def get_value_history(self):
        raise CORBA.NO_IMPLEMENT(0, CORBA.COMPLETED_NO)
        # *** Implement me
        # Must return: result


if __name__ == "__main__":
    import sys
    
    # Initialise the ORB
    orb = CORBA.ORB_init(sys.argv)
    
    # As an example, we activate an object in the Root POA
    poa = orb.resolve_initial_references("RootPOA")

    # Create an instance of a servant class
    servant = MyService_i()

    # Activate it in the Root POA
    poa.activate_object(servant)

    # Get the object reference to the object
    objref = servant._this()
    
    # Print a stringified IOR for it
    print orb.object_to_string(objref)

    # Activate the Root POA's manager
    poa._get_the_POAManager().activate()

    # Run the ORB, blocking this thread
    orb.run()

