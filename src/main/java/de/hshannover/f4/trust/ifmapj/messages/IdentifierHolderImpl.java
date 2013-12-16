package de.hshannover.f4.trust.ifmapj.messages;

/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de
 * 
 * This file is part of IfmapJ, version 1.0.0, implemented by the Trust@HsH
 * research group at the Hochschule Hannover.
 * 
 * IfmapJ is a lightweight, platform-independent, easy-to-use IF-MAP client
 * library for Java. IF-MAP is an XML based protocol for sharing data across
 * arbitrary components, specified by the Trusted Computing Group. IfmapJ is
 * maintained by the Trust@HsH group at the Hochschule Hannover. IfmapJ
 * was developed within the ESUKOM research project.
 * %%
 * Copyright (C) 2010 - 2013 Trust@HsH
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

import de.hshannover.f4.trust.ifmapj.identifier.Identifier;

class IdentifierHolderImpl implements IdentifierHolder {
	
	private Identifier mIdentifier1;
	
	private Identifier mIdentifier2;
	
	IdentifierHolderImpl() { }
	
	@Override
	public final Identifier getIdentifier1() {
		return mIdentifier1;
	}

	@Override
	public final void setIdentifier1(Identifier identifier1) {
		mIdentifier1 = identifier1;
	}

	@Override
	public final Identifier getIdentifier2() {
		return mIdentifier2;
	}

	@Override
	public final void setIdentifier2(Identifier identifier2) {
		mIdentifier2 = identifier2;
	}

	@Override
	public final boolean holdsLink() {
		return mIdentifier1 != null && mIdentifier2 != null;
	}

	@Override
	public final Identifier[] getIdentifier() {
		Identifier[] ret = new Identifier[2];
		ret[0] = mIdentifier1;
		ret[1] = mIdentifier2;
		return ret;
	}
}
