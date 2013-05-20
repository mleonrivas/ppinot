var MODELHANDLER_MODELS_URL = "service/models/";
var MODELHANDLER_MODEL_URL = "service/model/";

function ModelHandler() {
    this.modelsUrl = typeof ORYX == "undefined" ? MODELHANDLER_MODELS_URL : ORYX.CONFIG.MODEL_LIST_URL;
    this.processes = {};
    this.editors = {};
    this.models = {};
    this.modelsById = {};
    this.processLoaded = null;
}

(function($) {
    $.extend(ModelHandler.prototype, {

        loadModelsList: function() {
            var that = this;
            var queryingModels = new $.Deferred();
            $.ajax({
                type: "GET",
                url: this.modelsUrl,
                dataType: "json",
                success: function(data) {
                    that.models = data;
                    $(data).each(function() {
                        that.modelsById[this.modelId] = this;
                        that.processes[this.name] = this.url;
                        that.editors[this.name] = this.editor;
                    });
                    queryingModels.resolve(data);
                }
            });
            return queryingModels;
        },

        removeModel: function(id) {
            return $.ajax({
               type: "DELETE",
               url: this.modelsById[id].url
            });
        },

        addModel: function(model) {
            return $.ajax({
                    type: "POST",
                    url: this.modelsUrl,
                    contentType: "application/json",
                    data: $.toJSON(model),
                    success: function(data) {
                    }
                });

        }

    });
})(jQuery);
