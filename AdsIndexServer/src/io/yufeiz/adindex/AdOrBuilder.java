// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SelectAds.proto

package io.yufeiz.adindex;

public interface AdOrBuilder extends
    // @@protoc_insertion_point(interface_extends:adindex.Ad)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 adId = 1;</code>
   */
  long getAdId();

  /**
   * <code>int64 campaignId = 2;</code>
   */
  long getCampaignId();

  /**
   * <code>repeated string keyWords = 3;</code>
   */
  java.util.List<String>
      getKeyWordsList();
  /**
   * <code>repeated string keyWords = 3;</code>
   */
  int getKeyWordsCount();
  /**
   * <code>repeated string keyWords = 3;</code>
   */
  String getKeyWords(int index);
  /**
   * <code>repeated string keyWords = 3;</code>
   */
  com.google.protobuf.ByteString
      getKeyWordsBytes(int index);

  /**
   * <code>double relevanceScore = 4;</code>
   */
  double getRelevanceScore();

  /**
   * <code>double pClick = 5;</code>
   */
  double getPClick();

  /**
   * <code>double bidPrice = 6;</code>
   */
  double getBidPrice();

  /**
   * <code>double rankScore = 7;</code>
   */
  double getRankScore();

  /**
   * <code>double qualityScore = 8;</code>
   */
  double getQualityScore();

  /**
   * <code>double costPerClick = 9;</code>
   */
  double getCostPerClick();

  /**
   * <code>int32 position = 10;</code>
   */
  int getPosition();

  /**
   * <code>string title = 11;</code>
   */
  String getTitle();
  /**
   * <code>string title = 11;</code>
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <code>double price = 12;</code>
   */
  double getPrice();

  /**
   * <code>string thumbnail = 13;</code>
   */
  String getThumbnail();
  /**
   * <code>string thumbnail = 13;</code>
   */
  com.google.protobuf.ByteString
      getThumbnailBytes();

  /**
   * <code>string description = 14;</code>
   */
  String getDescription();
  /**
   * <code>string description = 14;</code>
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>string brand = 15;</code>
   */
  String getBrand();
  /**
   * <code>string brand = 15;</code>
   */
  com.google.protobuf.ByteString
      getBrandBytes();

  /**
   * <code>string detail_url = 16;</code>
   */
  String getDetailUrl();
  /**
   * <code>string detail_url = 16;</code>
   */
  com.google.protobuf.ByteString
      getDetailUrlBytes();

  /**
   * <code>string query = 17;</code>
   */
  String getQuery();
  /**
   * <code>string query = 17;</code>
   */
  com.google.protobuf.ByteString
      getQueryBytes();

  /**
   * <code>string category = 18;</code>
   */
  String getCategory();
  /**
   * <code>string category = 18;</code>
   */
  com.google.protobuf.ByteString
      getCategoryBytes();
}