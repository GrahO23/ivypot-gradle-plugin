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

import org.gradle.api.GradleException
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.FlatDirectoryArtifactRepository
import org.gradle.api.artifacts.repositories.IvyArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

/**
 * @author Schalk W. Cronjé
 */
class BaseRepositoryFactory implements org.gradle.api.internal.artifacts.BaseRepositoryFactory {
    @Override
    MavenArtifactRepository createJCenterRepository() {
        new JCenter()
    }

    @Override
    MavenArtifactRepository createMavenCentralRepository() {
        new MavenCentral()
    }

    MavenArtifactRepository createGoogleRepository() {
        new Google()
    }

    @Override
    MavenArtifactRepository createMavenRepository() {
        new MavenRepository()
    }

    @Override
    MavenArtifactRepository createMavenLocalRepository() {
        new MavenLocal()
    }

    @Override
    IvyArtifactRepository createIvyRepository() {
        new IvyRepository()
    }

    @Override
    FlatDirectoryArtifactRepository createFlatDirRepository() {
        return null
    }

    @Override
    ArtifactRepository createGradlePluginPortal() {
        return null
    }
}
