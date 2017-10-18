package com.tangzq.gitinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class GitCommitInfo {

    /**
     * ${git.tags} // comma separated tag names
     */
    String tags;

    /**
     * ${git.branch}
     */
    String branch;

    /**
     * ${git.dirty}
     */
    String dirty;

    /**
     * ${git.remote.origin.url}
     */
    String remoteOriginUrl;

    /**
     * ${git.commit.id.full} OR ${git.commit.id}
     */
    String commitId;

    /**
     * ${git.commit.id.abbrev}
     */
    String commitIdAbbrev;

    /**
     * ${git.commit.id.describe}
     */
    String describe;

    /**
     * ${git.commit.id.describe-short}
     */
    String describeShort;

    /**
     * ${git.commit.user.name}
     */
    String commitUserName;

    /**
     * ${git.commit.user.email}
     */
    String commitUserEmail;

    /**
     * ${git.commit.message.full}
     */
    String commitMessageFull;

    /**
     * ${git.commit.message.short}
     */
    String commitMessageShort;

    /**
     * ${git.commit.time}
     */
    String commitTime;

    /**
     * ${git.closest.tag.name}
     */
    String closestTagName;

    /**
     * ${git.closest.tag.commit.count}
     */
    String closestTagCommitCount;

    /**
     * ${git.build.user.name}
     */
    String buildUserName;

    /**
     * ${git.build.user.email}
     */
    String buildUserEmail;

    /**
     * ${git.build.time}
     */
    String buildTime;

    /**
     * ${git.build.host}
     */
    String buildHost;

    /**
     * ${git.build.version}
     */
    String buildVersion;

    public GitCommitInfo() {}
}
