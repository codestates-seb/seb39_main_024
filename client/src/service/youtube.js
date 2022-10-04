class Youtube {
  constructor(httpClient) {
    this.youtube = httpClient;
  }

  async workout() {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 10,
        type: 'video',
        q: '운동',
        videoDefinition: 'high',
        order: 'viewCount',
        regionCode: 'KR',
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }

  async search(query) {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 6,
        type: 'video',
        q: query,
        order: 'relevance',
        regionCode: 'KR',
        fields:
          'items(etag,id,snippet(channelTitle,thumbnails(high,medium),title))',
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }

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
}

export default Youtube;
