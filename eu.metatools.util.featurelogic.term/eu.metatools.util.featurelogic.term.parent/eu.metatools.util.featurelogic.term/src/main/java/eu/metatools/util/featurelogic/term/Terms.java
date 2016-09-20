package eu.metatools.util.featurelogic.term;

import eu.metatools.util.featurelogic.term.term.Term;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Terms
{
	private static final TermPrinter printer = new TermPrinter();

	static
	{
		new TermStandaloneSetup().createInjectorAndDoEMFRegistration();
	}

	public static Term read(String string)
	{
		ResourceSet resourceSet = new ResourceSetImpl();

		Resource resource = resourceSet.createResource(URI.createURI("dummy:/inmemory.flt"));

		try
		{
			resource.load(new ByteArrayInputStream(string.getBytes()), resourceSet.getLoadOptions());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return (Term) resource.getContents().get(0);
	}

	public static String print(Term term)
	{
		return printer.doSwitch(term);
	}

//	/**
//	 * Subsumes in direction left >= right
//	 * 
//	 * @param left
//	 * @param right
//	 * @return
//	 */
//	public static boolean subsumes(Term left, Term right)
//	{
//		if (right instanceof And)
//		{
//			And r = (And) right;
//
//			return r.getList().stream().map(x -> subsumes(left, x)).reduce(false, (a, b) -> a || b);
//		}
//
//		if (right instanceof Or)
//		{
//			Or r = (Or) right;
//
//			return r.getList().stream().map(x -> subsumes(left, x)).reduce(true, (a, b) -> a && b);
//		}
//
//		if (left instanceof And)
//		{
//			And r = (And) left;
//
//			return r.getList().stream().map(x -> subsumes(right, x)).reduce(true, (a, b) -> a && b);
//		}
//
//		if (left instanceof Or)
//		{
//			Or r = (Or) left;
//
//			return r.getList().stream().map(x -> subsumes(right, x)).reduce(false, (a, b) -> a || b);
//		}
//
//		Assign al = (Assign) left;
//
//		Assign ar = (Assign) right;
//
//		if (!al.getKey().equals(ar.getKey())) return false;
//		if (!al.getValue().equals(ar.getValue())) return false;
//
//		return true;
//	}
}
