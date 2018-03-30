// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SelectAds.proto

package io.yufeiz.adindex;

public final class searchAds {
  private searchAds() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_adindex_Query_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_adindex_Query_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_adindex_Ad_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_adindex_Ad_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_adindex_AdsRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_adindex_AdsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_adindex_AdsReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_adindex_AdsReply_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\017SelectAds.proto\022\007adindex\"\025\n\005Query\022\014\n\004t" +
      "erm\030\001 \003(\t\"\315\002\n\002Ad\022\014\n\004adId\030\001 \001(\003\022\022\n\ncampai" +
      "gnId\030\002 \001(\003\022\020\n\010keyWords\030\003 \003(\t\022\026\n\016relevanc" +
      "eScore\030\004 \001(\001\022\016\n\006pClick\030\005 \001(\001\022\020\n\010bidPrice" +
      "\030\006 \001(\001\022\021\n\trankScore\030\007 \001(\001\022\024\n\014qualityScor" +
      "e\030\010 \001(\001\022\024\n\014costPerClick\030\t \001(\001\022\020\n\010positio" +
      "n\030\n \001(\005\022\r\n\005title\030\013 \001(\t\022\r\n\005price\030\014 \001(\001\022\021\n" +
      "\tthumbnail\030\r \001(\t\022\023\n\013description\030\016 \001(\t\022\r\n" +
      "\005brand\030\017 \001(\t\022\022\n\ndetail_url\030\020 \001(\t\022\r\n\005quer" +
      "y\030\021 \001(\t\022\020\n\010category\030\022 \001(\t\"O\n\nAdsRequest\022",
      "\035\n\005query\030\001 \003(\0132\016.adindex.Query\022\020\n\010device" +
      "Id\030\002 \001(\t\022\020\n\010deviceIp\030\003 \001(\t\"#\n\010AdsReply\022\027" +
      "\n\002ad\030\001 \003(\0132\013.adindex.Ad2>\n\010AdsIndex\0222\n\006G" +
      "etAds\022\023.adindex.AdsRequest\032\021.adindex.Ads" +
      "Reply\"\000B&\n\021io.yufeiz.adindexB\tsearchAdsP" +
      "\001\242\002\003ADSb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_adindex_Query_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_adindex_Query_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_adindex_Query_descriptor,
        new String[] { "Term", });
    internal_static_adindex_Ad_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_adindex_Ad_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_adindex_Ad_descriptor,
        new String[] { "AdId", "CampaignId", "KeyWords", "RelevanceScore", "PClick", "BidPrice", "RankScore", "QualityScore", "CostPerClick", "Position", "Title", "Price", "Thumbnail", "Description", "Brand", "DetailUrl", "Query", "Category", });
    internal_static_adindex_AdsRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_adindex_AdsRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_adindex_AdsRequest_descriptor,
        new String[] { "Query", "DeviceId", "DeviceIp", });
    internal_static_adindex_AdsReply_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_adindex_AdsReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_adindex_AdsReply_descriptor,
        new String[] { "Ad", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
