package de.fhhannover.inform.trust.ifmapj.identifier;

/*
 * #%L
 * ====================================================
 *   _____                _     ____  _____ _   _ _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \|  ___| | | | | | |
 *    | | | '__| | | / __| __|/ / _` | |_  | |_| | |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _| |  _  |  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_|   |_| |_|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Fachhochschule Hannover 
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.inform.fh-hannover.de/
 * 
 * This file is part of IfmapJ, version 0.1.5, implemented by the Trust@FHH 
 * research group at the Fachhochschule Hannover.
 * 
 * IfmapJ is a lightweight, platform-independent, easy-to-use IF-MAP client
 * library for Java. IF-MAP is an XML based protocol for sharing data across
 * arbitrary components, specified by the Trusted Computing Group. IfmapJ is
 * maintained by the Trust@FHH group at the Fachhochschule Hannover. IfmapJ 
 * was developed within the ESUKOM research project.
 * %%
 * Copyright (C) 2010 - 2013 Trust@FHH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

/**
 * Represents an IF-MAP identifier with an administrative domain attribute.
 * 
 * @author aw
 *
 */
public abstract class IdentifierWithAd implements Identifier {
	
	private /* final */ String mAdministrativeDomain;

	protected IdentifierWithAd(String admDom) {
		mAdministrativeDomain = admDom;
	}

	/**
	 * Set to null or "" if no administrative-domain attribute is to be
	 * included.
	 * 
	 * @param administrativeDomain
	 * @deprecated
	 */
	public void setAdministrativeDomain(String administrativeDomain) {
		mAdministrativeDomain = administrativeDomain;
	}

	public String getAdministrativeDomain() {
		return mAdministrativeDomain;
	}

	@Override
	public boolean equals(Object o) {
		
		if (o == null)
			return false;
		
		if (!(o instanceof IdentifierWithAd))
			return false;
			
		IdentifierWithAd i = (IdentifierWithAd) o;
		
		if (this == i)
			return true;
	
		// This is mainly here if both are set to null.
		if (i.mAdministrativeDomain == mAdministrativeDomain)
			return true;
		
		if (i.mAdministrativeDomain != null && mAdministrativeDomain != null)
			return mAdministrativeDomain.equals(i.mAdministrativeDomain);
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		/* make life easier for the subclasses */
		if (getAdministrativeDomain() == null 
				|| getAdministrativeDomain().length() == 0)
			return "";
		else
			return ", " + getAdministrativeDomain();
	}
}
