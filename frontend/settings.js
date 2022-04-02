module.exports = {
    baseUrl: 'http://localhost:9093/',
    url: function (path) {
        return this.baseUrl;
    },
    isDebug: true,
};
