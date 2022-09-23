class Youtube {
  constructor(httpClient) {
    this.youtube = httpClient;
  }

  async workout() {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 5,
        type: 'video',
        q: '운동',
        videoDefinition: 'high',
        order: 'viewCount',
      },
    });
    // return response.data.items.id.videoId;
    return response.data.items;
  }

  async popular() {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 1,
        q: '홈트레이닝',
      },
    });
    return response.data.items;
  }

  async training() {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 1,
        q: '홈트레이닝',
      },
    });
    return response.data.items;
  }

  async stretching() {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 1,
        q: '스트레칭',
      },
    });
    return response.data.items;
  }

  // , contentDetails, statistics
  async videoData(videoId) {
    const response = await this.youtube.get('videos', {
      params: {
        part: 'snippet',
        id: videoId,
      },
    });
    return response.data.items.snippet;
  }

  async search(query) {
    const response = await this.youtube.get('search', {
      params: {
        part: 'snippet',
        maxResults: 1,
        type: 'video',
        q: query,
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }
}

export default Youtube;
