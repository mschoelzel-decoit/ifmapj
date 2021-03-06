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
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of ifmapj, version 2.2.0, implemented by the Trust@HsH
 * research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2014 Trust@HsH
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
package de.hshannover.f4.trust.ifmapj.identifier;

/**
 * Represents an IF-MAP ip-address identifier.
 *
 * @author aw
 *
 */
public class IpAddress extends IdentifierWithAd {

	private String mValue;
	private /* final */ IpAddressType mType;

	/**
	 * Package constructor.
	 */
	public IpAddress(IpAddressType type, String value, String admDom) {
		super(admDom);

		mType = type == null ? IpAddressType.IPv4 : type;

		setValue(value);
	}

	@Deprecated
	public void setType(IpAddressType type) {
		mType = type;
	}

	public IpAddressType getType() {
		return mType;
	}

	@Deprecated
	public final void setValue(String value) {
		mValue = value;
	}

	public String getValue() {
		return mValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("ip{%s, %s%s}", getValue(), getType(),
				super.toString());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mType == null) ? 0 : mType.hashCode());
		result = prime * result + ((mValue == null) ? 0 : mValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IpAddress other = (IpAddress) obj;
		if (mType != other.mType)
			return false;
		if (mValue == null) {
			if (other.mValue != null)
				return false;
		} else if (!mValue.equals(other.mValue))
			return false;
		return true;
	}


}
