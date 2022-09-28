class Youtube {
  constructor(httpClient) {
    this.youtube = httpClient;
  }

  // async workout() {
  //   const response = await this.youtube.get('search', {
  //     params: {
  //       part: 'snippet',
  //       maxResults: 5,
  //       type: 'video',
  //       q: '운동',
  //       videoDefinition: 'high',
  //       order: 'viewCount',
  //     },
  //   });
  //   // return response.data.items.id.videoId;
  //   return response.data.items;
  // }

  // , contentDetails, statistics
  async videoData(id) {
    const response = await this.youtube.get('videos', {
      params: {
        id: id,
        part: 'snippet, statistics',
        fields:
          'items(id,snippet(publishedAt,title,description,channelTitle),statistics(likeCount,viewCount))',
      },
    });
    return response.data.items;
  }

  async search(query) {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 6,
        type: 'video',
        q: query,
        videoDefinition: 'high',
        fields:
          'items(etag,id,snippet(channelTitle,thumbnails(high,medium),title))',
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }
}

export default Youtube;
