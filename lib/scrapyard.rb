require 'sinatra'
require 'sinatra/reloader'
require 'sinatra/activerecord'

module Scrapyard
  class Application < Sinatra::Base
    register Sinatra::ActiveRecordExtension

    configure :development do
      register Sinatra::Reloader
    end

    get '/' do
      "Foo bar"
    end
  end
end
