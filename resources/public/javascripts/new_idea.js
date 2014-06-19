var Scrapyard = Scrapyard || {};

Scrapyard.NewIdea = function(container, attrs) {
  var attrs = attrs || { needs: [], tools: [], constraints: [] };

  var needsSelect = container.find(".new-idea-form-needs");
  $(needsSelect).select2({
      tags: attrs.needs,
      tokenSeparators: [",", " "]
  });

  var toolsSelect = container.find(".new-idea-form-tools");
  $(toolsSelect).select2({
      tags: attrs.tools,
      tokenSeparators: [",", " "]
  });

  var constraintsSelect = container.find(".new-idea-form-constraints");
  $(constraintsSelect).select2({
      tags: attrs.constraints,
      tokenSeparators: [",", " "]
  });
};
