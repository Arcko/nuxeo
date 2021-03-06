/*
 * (C) Copyright 2006-2010 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Thierry Delprat
 */
package org.nuxeo.apidoc.snapshot;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.apidoc.api.SeamComponentInfo;
import org.nuxeo.apidoc.introspection.OperationInfoImpl;

public class SnapshotFilter {

    protected final String bundleGroupName;

    protected final List<String> bundlePrefixes = new ArrayList<>();

    protected final List<String> packagesPrefixes = new ArrayList<>();

    public SnapshotFilter(String groupName) {
        bundleGroupName = groupName;
    }

    public String getBundleGroupName() {
        return bundleGroupName;
    }

    public List<String> getBundlePrefixes() {
        return bundlePrefixes;
    }

    public void addBundlePrefix(String bundlePrefix) {
        bundlePrefixes.add(bundlePrefix);
    }

    public List<String> getPackagesPrefixes() {
        return packagesPrefixes;
    }

    public void addPackagesPrefix(String packagesPrefix) {
        packagesPrefixes.add(packagesPrefix);
    }

    public boolean includeBundleId(String bundleId) {
        for (String bprefix : bundlePrefixes) {
            if (bundleId.startsWith(bprefix)) {
                return true;
            }
        }
        return false;
    }

    public boolean includeSeamComponent(SeamComponentInfo seamComponent) {

        for (String iface : seamComponent.getInterfaceNames()) {
            for (String pprefix : packagesPrefixes) {
                if (iface.startsWith(pprefix)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean includeOperation(OperationInfoImpl op) {
        for (String pprefix : packagesPrefixes) {
            if (op.getOperationClass().startsWith(pprefix)) {
                return true;
            }
        }
        return false;
    }
}
