//
// ============================================================================
// (C) Copyright Schalk W. Cronje 2013-2018
//
// This software is licensed under the Apache License 2.0
// See http://www.apache.org/licenses/LICENSE-2.0 for license details
//
// Unless required by applicable law or agreed to in writing, software distributed under the License is
// distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and limitations under the License.
//
// ============================================================================
//

package org.ysb33r.gradle.ivypot.internal

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.gradle.api.Action
import org.gradle.api.ActionConfiguration
import org.gradle.api.artifacts.ComponentMetadataSupplier
import org.gradle.api.artifacts.ComponentMetadataVersionLister
import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.util.CollectionUtils
import org.ysb33r.gradle.ivypot.IvyXml

/**
 * @author Schalk W. Cronjé
 */
@CompileStatic
class MavenRepository implements MavenArtifactRepository, IvyXml, RepositoryTraits {

    /**
     * Returns the additional URLs to use to find artifact files. Note that these URLs are not used to find POM files.
     *
     * @return The additional URLs. Returns an empty list if there are no such URLs.
     */
    @Override
    @CompileDynamic
    Set<URI> getArtifactUrls() {
        CollectionUtils.stringize(this.artifactUrls).collect{ String it -> it.toURI() } as Set<URI>
    }

    /**
     * Adds some additional URLs to use to find artifact files. Note that these URLs are not used to find POM files.
     *
     * <p>The provided values are evaluated as per {@link org.gradle.api.Project#uri(Object)}. This means, for example, you can pass in a {@code File} object, or a relative path to be evaluated
     * relative to the project directory.
     *
     * @param urls The URLs to add.
     */
    @Override
    void artifactUrls(Object... urls) {
        artifactUrls.addAll(urls as List)
    }

    /**
     * Sets the additional URLs to use to find artifact files. Note that these URLs are not used to find POM files.
     *
     * <p>The provided values are evaluated as per {@link org.gradle.api.Project#uri(Object)}. This means, for example, you can pass in a {@code File} object, or a relative path to be evaluated
     * relative to the project directory.
     *
     * @param urls The URLs.
     */
//    @Override
    void setArtifactUrls(Set<URI> urls) {
        artifactUrls.clear()
        artifactUrls.addAll(urls)
    }

    /**
     * Sets the additional URLs to use to find artifact files. Note that these URLs are not used to find POM files.
     *
     * <p>The provided values are evaluated as per {@link org.gradle.api.Project#uri(Object)}. This means, for example, you can pass in a {@code File} object, or a relative path to be evaluated
     * relative to the project directory.
     *
     * @param urls The URLs.
     */
    @Override
    void setArtifactUrls(Iterable<?> urls) {
        artifactUrls.clear()
        artifactUrls.addAll(urls)
    }

    @Override
    void metadataSources(Action<? super MetadataSources> configureAction) {

    }
/** Returns a XML snippet suitable for including in the resolvers section
     *
     * @return
     */
    @Override
    String resolverXml() {
        // TODO: Test == [organisation]/[module]/[revision]/[artifact]-[revision].[ext]
//        String ret = "<url name='${name}' m2compatible='true'>"
//        ret+= "<artifact pattern='${url}/${IvyArtifactRepository.MAVEN_ARTIFACT_PATTERN}'/>"
//        getArtifactUrls().each { URI u ->
//            ret+= "<artifact pattern='${u}/${IvyArtifactRepository.MAVEN_ARTIFACT_PATTERN}'/>"
//        }
//        ret + '</url>'
        if(artifactUrls.size()) {
            String ret = "<chain name='${name}'><ibiblio name='${name}_root' m2compatible='true' root='${url}' usepoms='true'/>"
            getArtifactUrls().eachWithIndex { URI u,int index ->
                ret+= "<ibiblio name='${name}_${index}' m2compatible='true' root='${u}' usepoms='false'/>"
            }

            ret + '</chain>'

        } else {
            "<ibiblio name='${name}' m2compatible='true' root='${url}'/>"
        }

    }

    private List<Object> artifactUrls = []

    @Override
    void setMetadataSupplier(Class<? extends ComponentMetadataSupplier> rule) {

    }

    @Override
    void setMetadataSupplier(Class<? extends ComponentMetadataSupplier> rule, Action<? super ActionConfiguration> configureAction) {

    }

    @Override
    void setComponentVersionsLister(Class<? extends ComponentMetadataVersionLister> lister) {

    }

    @Override
    void setComponentVersionsLister(Class<? extends ComponentMetadataVersionLister> lister, Action<? super ActionConfiguration> configureAction) {

    }
}
