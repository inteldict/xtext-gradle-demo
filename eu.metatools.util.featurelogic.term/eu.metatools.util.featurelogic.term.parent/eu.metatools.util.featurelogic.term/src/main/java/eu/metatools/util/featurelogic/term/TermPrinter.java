package eu.metatools.util.featurelogic.term;

import eu.metatools.util.featurelogic.term.term.*;
import eu.metatools.util.featurelogic.term.term.util.TermSwitch;

import java.util.Iterator;

class TermPrinter extends TermSwitch<String>
{
	@Override
	public String caseAnd(And object)
	{
		if (object.getList().size() == 1) return doSwitch(object.getList().get(0));
		if (object.getList().size() == 0) return "()";

		StringBuilder sb = new StringBuilder("(");

		Iterator<Term> iterator = object.getList().iterator();

		sb.append(doSwitch(iterator.next()));

		while (iterator.hasNext())
		{
			sb.append("& " + doSwitch(iterator.next()));
		}

		sb.append(")");

		return sb.toString();
	}

	@Override
	public String caseAssign(Assign object)
	{
		return object.getKey() + "=" + object.getValue();
	}

	@Override
	public String caseOr(Or object)
	{
		if (object.getList().size() == 1) return doSwitch(object.getList().get(0));
		if (object.getList().size() == 0) return "()";

		StringBuilder sb = new StringBuilder("(");

		Iterator<Term> iterator = object.getList().iterator();

		sb.append(doSwitch(iterator.next()));

		while (iterator.hasNext())
		{
			sb.append("| " + doSwitch(iterator.next()));
		}

		sb.append(")");

		return sb.toString();
	}

	@Override
	public String caseNot(Not object)
	{
		return "!" + doSwitch(object.getIt());
	}

	@Override
	public String caseBottom(Bottom object)
	{
		return "BOTTOM";
	}

	@Override
	public String caseTop(Top object)
	{
		return "TOP";
	}
}
