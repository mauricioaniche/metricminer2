/**
 * Copyright 2014 Maurício Aniche

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.metricminer2.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Commit {

	private String hash;
	private Developer author;
	private Developer committer;
	private String msg;
	private List<Modification> modifications;
	private String parent;
	private Calendar date;
	private Set<String> branches;
	private boolean merge;
	private boolean inMasterBranch;

	public Commit(String hash, Developer author, Developer committer, Calendar date, String msg, String parent) {
		this(hash, author, committer, date, msg, parent, false, new HashSet<>(), false);
	}

	public Commit(String hash, Developer author, Developer committer, Calendar date, String msg, String parent, boolean merge, Set<String> branches, boolean isCommitInMasterBranch) {
		this.hash = hash;
		this.author = author;
		this.committer = committer;
		this.date = date;
		this.msg = msg;
		this.parent = parent;
		this.merge = merge;
		this.modifications = new ArrayList<Modification>();
		this.branches = branches;
		this.inMasterBranch = isCommitInMasterBranch;
	}

	public boolean isMerge() {
		return merge;
	}
	
	public String getHash() {
		return hash;
	}

	public Developer getAuthor() {
		return author;
	}

	public String getMsg() {
		return msg;
	}
	
	public Developer getCommitter() {
		return committer;
	}

	public String getParent() {
		return parent;
	}
	
	public void addModification(String oldPath, String newPath, ModificationType change, String diff, String sc) {
		Modification m = new Modification(oldPath, newPath, change, diff, sc);
		modifications.add(m);
		
	}
	
	public void addModifications(List<Modification> modifications) {
		this.modifications.addAll(modifications);
	}
	
	public List<Modification> getModifications() {
		return Collections.unmodifiableList(modifications);
	}

	@Override
	public String toString() {
		return "Commit [hash=" + hash + ", parent=" + parent + ", author=" + author + ", msg=" + msg + ", modifications="
				+ modifications + "]";
	}
	
	public Calendar getDate() {
		return date;
	}

	public Set<String> getBranches() {
		return Collections.unmodifiableSet(branches);
	}
	
	public boolean isInMasterBranch() {
		return inMasterBranch;
	}

}
