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
        q: '홈트레이닝',
        videoDefinition: 'high',
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
        maxResults: 5,
        type: 'video',
        q: query,
        videoDefinition: 'high',
      },
    });
    return response.data.items.map((item) => ({
      ...item,
      id: item.id.videoId,
    }));
  }
}

export default Youtube;
