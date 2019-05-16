package edu.hrbust.common.utils;

public interface DownloadListener {
    void success();

    void downloading(double progress);

    void failed(String error);
}
