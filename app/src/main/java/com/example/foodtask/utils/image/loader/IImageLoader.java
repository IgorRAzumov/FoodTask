package com.example.foodtask.utils.image.loader;

public interface IImageLoader<T> {
  void loadImageInTarget(String path, T target);
}
