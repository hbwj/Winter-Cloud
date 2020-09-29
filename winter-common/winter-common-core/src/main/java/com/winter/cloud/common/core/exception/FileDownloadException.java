package com.winter.cloud.common.core.exception;

/**
 * 文件下载异常
 *
 * @author MrBird
 */
public class FileDownloadException extends Exception {

    private static final long serialVersionUID = -5297265804341097403L;

    public FileDownloadException(String message) {
        super(message);
    }
}
