package org.repodriller.filter.commit;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.repodriller.domain.Commit;
import org.repodriller.domain.Developer;

public class OnlyNoMergeTest {

	@Test
	public void shouldAcceptIfCommitIsNotAMergeCommit() {
		Set<String> branches = new HashSet<>();
		branches.add("master");
		
		Calendar d = Calendar.getInstance();
		Commit regularCommit = new Commit("123", new Developer("Mau", "mau@mau.com"), new Developer("Mau", "mau@mau.com"), d,TimeZone.getDefault(), d, TimeZone.getDefault(), "x", null, false, branches, true, 0, 0f);
		Commit mergeCommit = new Commit("123", new Developer("Mau", "mau@mau.com"), new Developer("Mau", "mau@mau.com"), d, TimeZone.getDefault(), d, TimeZone.getDefault(), "x", null, true, branches, true, 0, 0f);

		Assert.assertTrue(new OnlyNoMerge().accept(regularCommit));
		Assert.assertFalse(new OnlyNoMerge().accept(mergeCommit));
	}
}
